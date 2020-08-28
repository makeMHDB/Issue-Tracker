package lt.bit.issueservice.model;

import java.util.Date;

public class CreateIssueRequest {

	private String issueSummary;
	private String issueDescription;
	private String priority;
	private Date identifiedDate;
	private Date targetResolutionDate;
	private Integer identifiedPersonIdInt;
	private Integer assignedToPersonIdInt;
	private Integer relatedProjectIdInt;

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

	public Date getIdentifiedDate() {
		return identifiedDate;
	}

	public void setIdentifiedDate(Date identifiedDate) {
		this.identifiedDate = identifiedDate;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getTargetResolutionDate() {
		return targetResolutionDate;
	}

	public void setTargetResolutionDate(Date targetResolutionDate) {
		this.targetResolutionDate = targetResolutionDate;
	}

	public Integer getIdentifiedPersonIdInt() {
		return identifiedPersonIdInt;
	}

	public void setIdentifiedPersonIdInt(Integer identifiedPersonIdInt) {
		this.identifiedPersonIdInt = identifiedPersonIdInt;
	}

	public Integer getAssignedToPersonIdInt() {
		return assignedToPersonIdInt;
	}

	public void setAssignedToPersonIdInt(Integer assignedToPersonIdInt) {
		this.assignedToPersonIdInt = assignedToPersonIdInt;
	}

	public Integer getRelatedProjectIdInt() {
		return relatedProjectIdInt;
	}

	public void setRelatedProjectIdInt(Integer relatedProjectIdInt) {
		this.relatedProjectIdInt = relatedProjectIdInt;
	}

}
