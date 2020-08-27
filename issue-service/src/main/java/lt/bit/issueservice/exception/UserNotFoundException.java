package lt.bit.issueservice.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6809338891767063128L;

	public UserNotFoundException(String message) {
		super("User not found: " + message);
	}

}
