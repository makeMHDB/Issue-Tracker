package lt.bit.issueservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.bit.issueservice.model.CloseIssueRequest;
import lt.bit.issueservice.model.CreateIssueRequest;
import lt.bit.issueservice.model.UpdateIssueRequest;
import lt.bit.issueservice.service.IssuesService;

@RestController
@RequestMapping("/issues")
public class IssuesController {

	private IssuesService issuesService;

	@Autowired
	public IssuesController(IssuesService issuesService) {
		super();
		this.issuesService = issuesService;
	}

	@PostMapping
	public ResponseEntity createNewIssue(@RequestBody CreateIssueRequest issueDetails, HttpServletRequest req) {
		issuesService.createNewIssue(issueDetails, req);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateIssue(@PathVariable Integer id, @RequestBody UpdateIssueRequest issueDetails,
			HttpServletRequest req) {
		issuesService.updateIssue(id, issueDetails, req);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteIssue(@PathVariable Integer id) {
		issuesService.deleteIssue(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}/close")
	public ResponseEntity closeIssue(@PathVariable Integer id, @RequestBody CloseIssueRequest issueDetails,
			HttpServletRequest req) {
		issuesService.closeIssue(id, issueDetails, req);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
