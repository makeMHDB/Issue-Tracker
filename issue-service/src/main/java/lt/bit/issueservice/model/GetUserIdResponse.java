package lt.bit.issueservice.model;

public class GetUserIdResponse {

	private Integer id;

	public GetUserIdResponse() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GetUserIdResponse [id=" + id + "]";
	}

}
