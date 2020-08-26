package lt.bit.issueservice.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 546394146652013592L;

	public UserAlreadyExistsException() {
		super("User already exists!");
	}

}
