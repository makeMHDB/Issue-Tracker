package lt.bit.usersservice.model;

public class CreatePersonRequest {

	private Integer userId;
	private String personName;
	private String personEmail;

	public CreatePersonRequest(Integer userId, String personName, String personEmail) {
		super();
		this.userId = userId;
		this.personName = personName;
		this.personEmail = personEmail;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

}
