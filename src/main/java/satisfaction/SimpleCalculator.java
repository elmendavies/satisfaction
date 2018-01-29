package satisfaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import satisfaction.Specification.Group;
import satisfaction.Specification.PassengerPreference;

/**
 * This calculator distributes the given groups in the order of appearing in the {@link Specification}.
 * It has two passes. The first one assigns seats to passengers that are fully satisfied. Then,
 * in a second pass, the rest of passengers are assigned in order until the plain is full. 
 * @author mme
 *
 */
public class SimpleCalculator implements DistributionCalculator {

	/**
	 * @see {@link DistributionCalculator}
	 */
	@Override
	public Distribution calculateFor(Specification specification) {
		
		// Create a copy to work with.
		List<Group> candidates = new ArrayList<>(specification.groups);
		
		PassengerId[][] assignments = new PassengerId[specification.rows][specification.cols];
		
		// First and second passes.
		firstPass(specification, candidates, assignments);
		secondPass(specification, candidates, assignments);
		
		return new Distribution(assignments);
	}

	/**
	 * These method tries to assign passengers that will be fully satisfied.
	 * @param specification The specification.
	 * @param candidates Input/output parameter with the candidates.
	 * @param assignments Output parameter with the arrays of assignments.
	 */
	private void firstPass(Specification specification, List<Group> candidates, PassengerId[][] assignments) {
		
		int row = 0;
		int column = 0;
		
		/**
		 * Cursor will move on the assignments like a typewriter, picking a group and filling the
		 * assignment with it. If there is no group that fulfills the hole the algorithm goes to the next line. 
		 */
		while (row < specification.rows) {
			
			int space = specification.cols - column;
			boolean leftAvailable = column == 0;
			
			// At least, if using the whole space, the available windows is 1.
			int availableWindows = 1;
			
			// If left is available, then add it to the total of avaiable windows.
			if (leftAvailable)
				availableWindows++;
			
			// Get a candidate group.
			Group group = pickUpCandidate(candidates, space, availableWindows);
			
			if (group != null) {
				// Hack for special case: if only a window is needed, move the passenger to the right windows
				if (group.windowsNeeded() == 1 && space == group.size()) 
					leftAvailable = false;
				
				// Copy passengers and get the new column value.
				copyPassengers(group, assignments, row, column, leftAvailable);
				
				// Update
				column += group.size();
				candidates.remove(group);
			}
			
			// If no group was electable or row is completed, advance to next row.   
			if (group == null || column == specification.cols) {
				column = 0;
				row++;
			}
		}
	}
	
	/**
	 * This method assigns as much passengers as possible that could not be fully satisfied to the free seats.
	 * @param specification
	 * @param candidates
	 * @param assignments
	 */
	private void secondPass(Specification specification, List<Group> candidates, PassengerId[][] assignments) {
		List<PassengerPreference> remaining = new ArrayList<>();
		
		for (Group group : candidates) 
			remaining.addAll(group);
		
		Iterator<PassengerPreference> it = remaining.iterator();
		for (int i = 0; i < specification.rows; i++) {
			for (int j = 0; j < specification.cols; j++) {
				if (assignments[i][j] != null)
					continue;
				
				if (! it.hasNext())
					return;
				
				assignments[i][j] = it.next().passangerId();
			}			
		}
	}

	/**
	 * Looks for a candidate.
	 * @param candidates
	 * @param space
	 * @param availableWindows
	 * @return
	 */
	private Group pickUpCandidate(List<Group> candidates, int space, int availableWindows) {
		for (Group group : candidates) {
			int size = group.size();
			
			// Does not fit so continue
			if (size > space)
				continue;
			
			// Does not use all space and wants more windows that available.
			if (size < space && group.windowsNeeded() >= availableWindows)
				continue;
			
			return group;
		}
		return null;
	}

	/**
	 * Copy passengers to the assigned space. Tries to assign according to window preferences. 
	 * @param group
	 * @param assignments
	 * @param row
	 * @param column
	 * @param leftAvailable
	 */
	private void copyPassengers(Group group, PassengerId[][] assignments, int row, int column, boolean leftAvailable) {
		
		// Passengers with window preferences.
		Iterator<PassengerPreference> windowPassengers 
			= group.stream().filter(preference -> preference.windowPrefered()).iterator();
		
		// Passengers without window preference.
		Iterator<PassengerPreference> restOfPassengers 
			= group.stream().filter(preference -> ! preference.windowPrefered()).iterator();
		
		// If the left window is available assign it to first of window passengers.
		if (leftAvailable && windowPassengers.hasNext())
			assignments[row][column++] = windowPassengers.next().passangerId();
		
		// Next, add the passengers without window preference. 
		while (restOfPassengers.hasNext()) 
			assignments[row][column++] = restOfPassengers.next().passangerId();
		
		// Next, add the rest of window preference passengers.
		while (windowPassengers.hasNext()) 
			assignments[row][column++] = windowPassengers.next().passangerId();
	}

}
