package lt.bit.issueservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public final StackTraceElement[] handleNullPointerException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return ex.getStackTrace();
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProjectAlreadyExistsException.class)
	public final ResponseEntity<ExceptionResponse> handleProjectAlreadyExistsException(Exception ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.FOUND);
	}

	@ExceptionHandler(ProjectNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleProjectNotFoundException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IssueAlreadyExistsException.class)
	public final ResponseEntity<ExceptionResponse> handleIssueAlreadyExistsException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.FOUND);
	}

	@ExceptionHandler(IssueNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleIssueNotFoundException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
