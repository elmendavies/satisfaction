package satisfaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Identifier of a passenger.
 * @author mme
 *
 */
public class PassengerId {
	
	final int passengerNumber;
	
	/**
	 * Private contructor. Should not be used outside this class.
	 * @param passengerNumber
	 */
	private PassengerId(int passengerNumber) {
		this.passengerNumber = passengerNumber;
	}
	
	/**
	 * A factory method to contruct the passanger ID. Checks 
	 * that the passengerNumber is correct.
	 * @param passangerNumber A natural number representing the passenger.
	 * @return
	 * @throws ValidationException
	 */
	public static PassengerId of(int passangerNumber) throws ValidationException {
		if(passangerNumber <= 0) {
			throw new ValidationException("Invalid passager number (it must be greater that zero): " + passangerNumber);
		}
		return new PassengerId(passangerNumber);
	}

	/**
	 * Property accessor.
	 * @return
	 */
	public int passengerNumber() {
		return passengerNumber;
	}
	
	/**
	 * Value object implementation requirement.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + passengerNumber;
		return result;
	}

	/**
	 * Value object implementation requirement.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PassengerId other = (PassengerId) obj;
		if (passengerNumber != other.passengerNumber)
			return false;
		return true;
	}

	public static List<PassengerId> createList(Integer...passengerNumbers) throws ValidationException {
		
		ArrayList<PassengerId> list = new ArrayList<PassengerId>();
		for (Integer passengerNumber : passengerNumbers) {
			if (passengerNumber != null)
				list.add(PassengerId.of(passengerNumber));
			else 
				list.add(null);
		}
		return list;
	}
	
	@Override
	public String toString() {
		return Integer.toString(passengerNumber);
	}

}
