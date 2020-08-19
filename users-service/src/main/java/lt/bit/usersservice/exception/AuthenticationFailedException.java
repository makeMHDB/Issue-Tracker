package lt.bit.usersservice.exception;

public class AuthenticationFailedException extends RuntimeException {

	private static final long serialVersionUID = -5583201958285952258L;

	public AuthenticationFailedException() {
		super("Username and/or password is incorrect!");
	}

}
