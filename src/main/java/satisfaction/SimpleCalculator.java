package satisfaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import satisfaction.Specification.Group;
import satisfaction.Specification.PassengerPreference;

public class SimpleCalculator implements DistributionCalculator {

	@Override
	public Distribution calculate(Specification specification) {
		
		List<Group> candidates = new ArrayList<>(specification.groups);
		
		PassengerId[][] assignments = new PassengerId[specification.rows][specification.rows];
		
		firstPass(specification, candidates, assignments);
		secondPass(specification, candidates, assignments);
		
		return new Distribution(assignments);
	}

	private void firstPass(Specification specification, List<Group> candidates, PassengerId[][] assignments) {
		
		int row = 0;
		int column = 0;
		
		/**
		 * Cursor will move on the asignments like a typewriter, picking a group and filling the
		 * assignment with it. If there is no group that fulfills the hole the algorithm goes to the next line. 
		 */
		while (row < specification.rows) {
			
			int space = specification.cols - column;
			boolean leftAvailable = column == 0;
			
			Group group = pickUpCandidate(candidates, space, leftAvailable);
			
			if (group != null) {
				if (group.windowsNeeded() == 1 && space == group.size()) 
					leftAvailable = false;
				
				column = copyPassengers(group, assignments, row, column, leftAvailable);
				
				candidates.remove(group);
			}
			
			if (group == null || column == specification.cols) {
				column = 0;
				row++;
			}
		}
	}
	
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

	private Group pickUpCandidate(List<Group> candidates, int space, boolean leftAvailable) {
		for (Group group : candidates) {
			int size = group.size();
			
			// Does not fit so continue
			if (size > space)
				continue;
			
			// Does not use all space and wants window.
			if (size < space && group.windowsNeeded() > 0 && ! leftAvailable)
				continue;
			
			return group;
		}
		return null;
	}

	private int copyPassengers(Group group, PassengerId[][] assignments, int row, int column, boolean leftAvailable) {
		Iterator<PassengerPreference> windowPassengers = group.stream().filter(preference -> preference.windowPrefered()).iterator();
		Iterator<PassengerPreference> restOfPassengers = group.stream().filter(preference -> ! preference.windowPrefered()).iterator();
		
		if (leftAvailable && windowPassengers.hasNext())
			assignments[row][column++] = windowPassengers.next().passangerId();
		
		while (restOfPassengers.hasNext()) 
			assignments[row][column++] = restOfPassengers.next().passangerId();
		
		if (windowPassengers.hasNext()) 
			assignments[row][column++] = windowPassengers.next().passangerId();
		return column;
	}

}
