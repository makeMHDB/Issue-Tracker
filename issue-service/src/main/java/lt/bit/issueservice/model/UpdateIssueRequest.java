package lt.bit.issueservice.model;

import java.util.Date;

public class UpdateIssueRequest {

	private String issueSummary;
	private String issueDescription;
	private String priority;
	private String status;
	private Date targetResolutionDate;
	private Integer assignedToPersonIdInt;

	public String getIssueSummary() {
		return issueSummary;
	}

	public void setIssueSummary(String issueSummary) {
		this.issueSummary = issueSummary;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTargetResolutionDate() {
		return targetResolutionDate;
	}

	public void setTargetResolutionDate(Date targetResolutionDate) {
		this.targetResolutionDate = targetResolutionDate;
	}

	public Integer getAssignedToPersonIdInt() {
		return assignedToPersonIdInt;
	}

	public void setAssignedToPersonIdInt(Integer assignedToPersonIdInt) {
		this.assignedToPersonIdInt = assignedToPersonIdInt;
	}

}
