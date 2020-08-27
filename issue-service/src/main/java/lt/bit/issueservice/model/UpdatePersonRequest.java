package lt.bit.issueservice.model;

public class UpdatePersonRequest {

	private String personName;
	private String personEmail;
	private Integer assignedProject;

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public Integer getAssignedProject() {
		return assignedProject;
	}

	public void setAssignedProject(Integer assignedProject) {
		this.assignedProject = assignedProject;
	}
}
