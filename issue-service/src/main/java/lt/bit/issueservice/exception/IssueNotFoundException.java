package lt.bit.issueservice.exception;

public class IssueNotFoundException extends RuntimeException {

	public IssueNotFoundException(String message) {
		super("Issue not found: " + message);
	}
}
