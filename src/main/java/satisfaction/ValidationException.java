package satisfaction;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Exception indicating parsing or validation problems encountered.
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}

}
