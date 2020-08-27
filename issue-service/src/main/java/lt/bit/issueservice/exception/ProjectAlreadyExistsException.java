package lt.bit.issueservice.exception;

public class ProjectAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -8369601061977905268L;

	public ProjectAlreadyExistsException() {
		super("This project already exist!");
	}

}
