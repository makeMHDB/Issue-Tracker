package lt.bit.issueservice.service;

import javax.servlet.http.HttpServletRequest;

import lt.bit.issueservice.model.CloseIssueRequest;
import lt.bit.issueservice.model.CreateIssueRequest;
import lt.bit.issueservice.model.UpdateIssueRequest;

public interface IssuesService {

	void createNewIssue(CreateIssueRequest issueDetails, HttpServletRequest req);

	void updateIssue(Integer id, UpdateIssueRequest issueDetails, HttpServletRequest req);

	void deleteIssue(Integer id);

	void closeIssue(Integer id, CloseIssueRequest issueDetails, HttpServletRequest req);

}
