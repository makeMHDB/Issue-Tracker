package lt.bit.issueservice.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author makeMH
 */
@Entity
@Table(name = "people")
@NamedQueries({ @NamedQuery(name = "People.findAll", query = "SELECT p FROM People p"),
		@NamedQuery(name = "People.findByPersonId", query = "SELECT p FROM People p WHERE p.personId = :personId"),
		@NamedQuery(name = "People.findByUserId", query = "SELECT p FROM People p WHERE p.userId = :userId"),
		@NamedQuery(name = "People.findByPersonName", query = "SELECT p FROM People p WHERE p.personName = :personName"),
		@NamedQuery(name = "People.findByPersonEmail", query = "SELECT p FROM People p WHERE p.personEmail = :personEmail"),
		@NamedQuery(name = "People.findByAssignedProject", query = "SELECT p FROM People p WHERE p.assignedProject = :assignedProject") })
public class People implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "person_id")
	private Integer personId;
	@Basic(optional = false)
	@Column(name = "user_id")
	private int userId;
	@Basic(optional = false)
	@Column(name = "person_name")
	private String personName;
	@Basic(optional = false)
	@Column(name = "person_email")
	private String personEmail;
	@Column(name = "assigned_project")
	private Integer assignedProject;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "identifiedByPersonId")
	@JsonIgnore
	private List<Issues> identifiedIssuesList;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "assignedToPersonId")
	private List<Issues> assignedIssuesList;

	public People() {
	}

	public People(Integer personId) {
		this.personId = personId;
	}

	public People(Integer personId, int userId, String personName, String personEmail) {
		this.personId = personId;
		this.userId = userId;
		this.personName = personName;
		this.personEmail = personEmail;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

	public Integer getAssignedProject() {
		return assignedProject;
	}

	public void setAssignedProject(Integer assignedProject) {
		this.assignedProject = assignedProject;
	}

	public List<Issues> getIssuesList() {
		return identifiedIssuesList;
	}

	public void setIssuesList(List<Issues> issuesList) {
		this.identifiedIssuesList = issuesList;
	}

	public List<Issues> getIssuesList1() {
		return assignedIssuesList;
	}

	public void setIssuesList1(List<Issues> issuesList1) {
		this.assignedIssuesList = issuesList1;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (personId != null ? personId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof People)) {
			return false;
		}
		People other = (People) object;
		if ((this.personId == null && other.personId != null)
				|| (this.personId != null && !this.personId.equals(other.personId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "lt.bit.bandomasisspring.test.People[ personId=" + personId + " ]";
	}

}
