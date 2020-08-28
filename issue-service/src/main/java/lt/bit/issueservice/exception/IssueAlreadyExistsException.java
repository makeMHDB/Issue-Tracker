package lt.bit.issueservice.exception;

public class IssueAlreadyExistsException extends RuntimeException {

	public IssueAlreadyExistsException() {
		super("Issue already exist in that project");
	}
}
