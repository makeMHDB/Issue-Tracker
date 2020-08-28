package lt.bit.issueservice.exception;

public class ProjectNotFoundException extends RuntimeException {

	public ProjectNotFoundException(String message) {
		super("Project not found: " + message);
	}

}
