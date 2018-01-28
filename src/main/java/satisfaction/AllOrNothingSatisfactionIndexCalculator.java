package satisfaction;

import java.util.SortedSet;
import java.util.TreeSet;

import satisfaction.Specification.Group;
import satisfaction.Specification.PassengerPreference;

public class AllOrNothingSatisfactionIndexCalculator implements SatisfactionIndexCalculator {

	@Override
	public SatisfactionIndex satisfactionIndexOf(Specification specification, Distribution distribution) {
		
		int total = 0;
		int satisfied = 0;
		
		for (Group group: specification.groups()) {
			total += group.size();
			satisfied += satisfiedPassengers(group, distribution);
		}
		
		return new SatisfactionIndex(total, satisfied);
	}
	
	/**
	 * Calculates the number of satisfied passengers in the provided distribution.
	 * @param distribution
	 * @return
	 */
	public int satisfiedPassengers(Group group, Distribution distribution) {
		
		int total = 0;
		Integer rowOfGroup = null;
		SortedSet<Integer> colsOfGroup = new TreeSet<>();
		
		// 1. Walk through the passengers gathering info and checking most of things.
		for (PassengerPreference passanger: group) {
			
			Distribution.Cursor coordinates = distribution.coordinatesOf(passanger.passangerId());
			if (coordinates == null) 
				continue;
				
			if (rowOfGroup == null)
				// If first time, initialize group's row number,
				rowOfGroup = coordinates.row();
			else if (rowOfGroup != coordinates.row())
				// If it isn't the first time and the row number isn't the same return 0. 
				return 0;
			
			// Add the column to the list of columns used by the group.
			colsOfGroup.add(coordinates.column());
			
			// If the passanger doesn't have preferrence or it is satified, annotate. 
			if (! passanger.windowPrefered() || coordinates.isInWindow())
				total += 1;
				
		}
		
		// 2. Check that the group is altogether. If not no one if satisfied.
		Integer last = null;
		for (Integer i : colsOfGroup) {
			if (last == null)
				last = i;
			else if (++last != i) 
				// If previous and current aren't consecutive no one is satisfied.
				return 0;
		}
		
		return total;
	}
}
