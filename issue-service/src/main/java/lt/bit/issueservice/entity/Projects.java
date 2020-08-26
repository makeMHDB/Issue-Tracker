package lt.bit.issueservice.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author makeMH
 */
@Entity
@Table(name = "projects")
@NamedQueries({ @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p"),
		@NamedQuery(name = "Projects.findByProjectId", query = "SELECT p FROM Projects p WHERE p.projectId = :projectId"),
		@NamedQuery(name = "Projects.findByProjectName", query = "SELECT p FROM Projects p WHERE p.projectName = :projectName"),
		@NamedQuery(name = "Projects.findByStartDate", query = "SELECT p FROM Projects p WHERE p.startDate = :startDate"),
		@NamedQuery(name = "Projects.findByTargetEndDate", query = "SELECT p FROM Projects p WHERE p.targetEndDate = :targetEndDate"),
		@NamedQuery(name = "Projects.findByActualEndDate", query = "SELECT p FROM Projects p WHERE p.actualEndDate = :actualEndDate"),
		@NamedQuery(name = "Projects.findByCreatedOn", query = "SELECT p FROM Projects p WHERE p.createdOn = :createdOn"),
		@NamedQuery(name = "Projects.findByCreatedBy", query = "SELECT p FROM Projects p WHERE p.createdBy = :createdBy"),
		@NamedQuery(name = "Projects.findByModifiedOn", query = "SELECT p FROM Projects p WHERE p.modifiedOn = :modifiedOn"),
		@NamedQuery(name = "Projects.findByModifiedBy", query = "SELECT p FROM Projects p WHERE p.modifiedBy = :modifiedBy") })
public class Projects implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "project_id")
	private Integer projectId;
	@Basic(optional = false)
	@Column(name = "project_name")
	private String projectName;
	@Basic(optional = false)
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Basic(optional = false)
	@Column(name = "target_end_date")
	@Temporal(TemporalType.DATE)
	private Date targetEndDate;
	@Column(name = "actual_end_date")
	@Temporal(TemporalType.DATE)
	private Date actualEndDate;
	@Basic(optional = false)
	@Column(name = "created_on")
	@Temporal(TemporalType.DATE)
	private Date createdOn;
	@Basic(optional = false)
	@Column(name = "created_by")
	private String createdBy;
	@Basic(optional = false)
	@Column(name = "modified_on")
	@Temporal(TemporalType.DATE)
	private Date modifiedOn;
	@Basic(optional = false)
	@Column(name = "modified_by")
	private String modifiedBy;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "relatedProjectId")
	private List<Issues> issuesList;

	public Projects() {
	}

	public Projects(Integer projectId) {
		this.projectId = projectId;
	}

	public Projects(Integer projectId, String projectName, Date startDate, Date targetEndDate, Date createdOn,
			String createdBy, Date modifiedOn, String modifiedBy) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.targetEndDate = targetEndDate;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getTargetEndDate() {
		return targetEndDate;
	}

	public void setTargetEndDate(Date targetEndDate) {
		this.targetEndDate = targetEndDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<Issues> getIssuesList() {
		return issuesList;
	}

	public void setIssuesList(List<Issues> issuesList) {
		this.issuesList = issuesList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (projectId != null ? projectId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Projects)) {
			return false;
		}
		Projects other = (Projects) object;
		if ((this.projectId == null && other.projectId != null)
				|| (this.projectId != null && !this.projectId.equals(other.projectId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "lt.bit.bandomasisspring.test.Projects[ projectId=" + projectId + " ]";
	}

}
