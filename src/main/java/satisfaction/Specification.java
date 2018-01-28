package satisfaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the input for a {@link DistributionCalculator}. 
 * @author mme
 *
 */
public class Specification {
	int rows;
	int cols;
	List<Group> groups;
	
	public Specification(int rows, int cols, List<Group> groups) {
		super();
		this.rows = rows;
		this.cols = cols;
		this.groups = groups;
	}

	public static Specification parseSpecification(String string) throws ValidationException {
		
		final String[] lines = string.split("\n");
		
		if (lines.length == 0)
			throw new ValidationException("Incorrect number of input lines.");
		
		final String[] firstLine = lines[0].split(" ");
		
		if (firstLine.length != 2)
			throw new ValidationException("First line must be two numbers separate by space.");
		
		final int rows = Integer.parseInt(firstLine[0]);
		
		if (rows <= 0)
			throw new ValidationException("Rows value must be a natural number.");
		
		final int cols = Integer.parseInt(firstLine[1]);
		
		if (cols <= 0)
			throw new ValidationException("Colums value must be a natural number.");
		
		final List<Group> groups = new ArrayList<>();
		
		for (int i = 0; i < lines.length - 1; i++) {
			groups.add(Group.parseGroup(lines[i + 1]));
		}
		
		return new Specification(rows, cols, groups);
	}

	/**
	 * Property accessors.
	 * @return
	 */
	public List<Group> groups() { return groups; }
	public int cols() {	return cols; }	
	public int rows() { return rows; }

	/**
	 * List of passenger preferences.
	 * @author mme
	 *
	 */
	public static class Group extends ArrayList<PassengerPreference> {
	
		private static final long serialVersionUID = 1L;
	
		/**
		 * Parses a group from a line specification.
		 * @param lineSpecification
		 * @return
		 * @throws ValidationException
		 */
		public static Group parseGroup(String lineSpecification) throws ValidationException {
			Group group = new Group();
			for(String token : lineSpecification.split(" ")) {
				group.add(PassengerPreference.parsePassanger(token));
			}
			return group;
		}
	
		public int windowsNeeded() {
			return (int) stream().filter(preference -> preference.windowPrefered()).count();
		}
	}

	/**
	 * A container for the passenger ID and the prefered window.
	 * @author mme
	 *
	 */
	public static class PassengerPreference {
		
		private final PassengerId passengerId;
		private final boolean windowPrefered;
		
		public PassengerPreference(PassengerId passengerId, boolean windowPrefered) {
			super();
			this.passengerId = passengerId;
			this.windowPrefered = windowPrefered;
		}
		
		public static PassengerPreference parsePassanger(String specification) throws ValidationException {
			
			int passangerNumber = Integer.parseInt(specification.replace("W", ""));
			boolean windowPreference = specification.endsWith("W");
			
			return new PassengerPreference(PassengerId.of(passangerNumber), windowPreference);
		}
	
		public boolean windowPrefered() {
			return windowPrefered;
		}
	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((passengerId == null) ? 0 : passengerId.hashCode());
			result = prime * result + (windowPrefered ? 1231 : 1237);
			return result;
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PassengerPreference other = (PassengerPreference) obj;
			if (passengerId == null) {
				if (other.passengerId != null)
					return false;
			} else if (!passengerId.equals(other.passengerId))
				return false;
			if (windowPrefered != other.windowPrefered)
				return false;
			return true;
		}
	
		public PassengerId passangerId() {
			return passengerId;
		}
		
		public String toString() {
			return passengerId.toString() + (windowPrefered() ? "W" : "");
		}
	}

}
