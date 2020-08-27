package lt.bit.issueservice.model;

import java.util.Date;

public class CreateProjectRequest {

	private String projectName;
	private Date targetEndDate;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getTargetEndDate() {
		return targetEndDate;
	}

	public void setTargetEndDate(Date targetEndDate) {
		this.targetEndDate = targetEndDate;
	}

}
