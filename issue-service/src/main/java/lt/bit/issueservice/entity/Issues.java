package lt.bit.issueservice.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author makeMH
 */
@Entity
@Table(name = "issues")
@NamedQueries({ @NamedQuery(name = "Issues.findAll", query = "SELECT i FROM Issues i"),
		@NamedQuery(name = "Issues.findByIssueId", query = "SELECT i FROM Issues i WHERE i.issueId = :issueId"),
		@NamedQuery(name = "Issues.findByIssueSummary", query = "SELECT i FROM Issues i WHERE i.issueSummary = :issueSummary"),
		@NamedQuery(name = "Issues.findByIssueSummaryAndProject", query = "SELECT i FROM Issues i WHERE i.issueSummary = :issueSummary AND i.relatedProjectId = :relatedProjectId"),
		@NamedQuery(name = "Issues.findByIssueDescription", query = "SELECT i FROM Issues i WHERE i.issueDescription = :issueDescription"),
		@NamedQuery(name = "Issues.findByIdentifiedDate", query = "SELECT i FROM Issues i WHERE i.identifiedDate = :identifiedDate"),
		@NamedQuery(name = "Issues.findByStatus", query = "SELECT i FROM Issues i WHERE i.status = :status"),
		@NamedQuery(name = "Issues.findByPriority", query = "SELECT i FROM Issues i WHERE i.priority = :priority"),
		@NamedQuery(name = "Issues.findByTargetResolutionDate", query = "SELECT i FROM Issues i WHERE i.targetResolutionDate = :targetResolutionDate"),
		@NamedQuery(name = "Issues.findByActualResolutionDate", query = "SELECT i FROM Issues i WHERE i.actualResolutionDate = :actualResolutionDate"),
		@NamedQuery(name = "Issues.findByResolutionSummary", query = "SELECT i FROM Issues i WHERE i.resolutionSummary = :resolutionSummary"),
		@NamedQuery(name = "Issues.findByCreatedOn", query = "SELECT i FROM Issues i WHERE i.createdOn = :createdOn"),
		@NamedQuery(name = "Issues.findByCreatedBy", query = "SELECT i FROM Issues i WHERE i.createdBy = :createdBy"),
		@NamedQuery(name = "Issues.findByModifiedOn", query = "SELECT i FROM Issues i WHERE i.modifiedOn = :modifiedOn"),
		@NamedQuery(name = "Issues.findByModifiedBy", query = "SELECT i FROM Issues i WHERE i.modifiedBy = :modifiedBy") })
public class Issues implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "issue_id")
	private Integer issueId;
	@Basic(optional = false)
	@Column(name = "issue_summary")
	private String issueSummary;
	@Basic(optional = false)
	@Column(name = "issue_description")
	private String issueDescription;
	@Basic(optional = false)
	@Column(name = "identified_date")
	@Temporal(TemporalType.DATE)
	private Date identifiedDate;
	@Basic(optional = false)
	@Column(name = "status")
	private String status;
	@Basic(optional = false)
	@Column(name = "priority")
	private String priority;
	@Basic(optional = false)
	@Column(name = "target_resolution_date")
	@Temporal(TemporalType.DATE)
	private Date targetResolutionDate;
	@Column(name = "actual_resolution_date")
	@Temporal(TemporalType.DATE)
	private Date actualResolutionDate;
	@Basic(optional = false)
	@Column(name = "resolution_summary")
	private String resolutionSummary;
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
	@JoinColumn(name = "identified_by_person_id", referencedColumnName = "person_id")
	@ManyToOne(optional = false)
	private People identifiedByPersonId;
	@JoinColumn(name = "assigned_to_person_id", referencedColumnName = "person_id")
	@ManyToOne(optional = false)
	private People assignedToPersonId;
	@JoinColumn(name = "related_project_id", referencedColumnName = "project_id")
	@ManyToOne(optional = false)
	private Projects relatedProjectId;

	public Issues() {
	}

	public Issues(Integer issueId) {
		this.issueId = issueId;
	}

	public Issues(Integer issueId, String issueSummary, String issueDescription, Date identifiedDate, String status,
			String priority, Date targetResolutionDate, String resolutionSummary, Date createdOn, String createdBy,
			Date modifiedOn, String modifiedBy) {
		this.issueId = issueId;
		this.issueSummary = issueSummary;
		this.issueDescription = issueDescription;
		this.identifiedDate = identifiedDate;
		this.status = status;
		this.priority = priority;
		this.targetResolutionDate = targetResolutionDate;
		this.resolutionSummary = resolutionSummary;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

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

	public Date getIdentifiedDate() {
		return identifiedDate;
	}

	public void setIdentifiedDate(Date identifiedDate) {
		this.identifiedDate = identifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
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

	public Date getActualResolutionDate() {
		return actualResolutionDate;
	}

	public void setActualResolutionDate(Date actualResolutionDate) {
		this.actualResolutionDate = actualResolutionDate;
	}

	public String getResolutionSummary() {
		return resolutionSummary;
	}

	public void setResolutionSummary(String resolutionSummary) {
		this.resolutionSummary = resolutionSummary;
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

	public People getIdentifiedByPersonId() {
		return identifiedByPersonId;
	}

	public void setIdentifiedByPersonId(People identifiedByPersonId) {
		this.identifiedByPersonId = identifiedByPersonId;
	}

	public People getAssignedToPersonId() {
		return assignedToPersonId;
	}

	public void setAssignedToPersonId(People assignedToPersonId) {
		this.assignedToPersonId = assignedToPersonId;
	}

	public Projects getRelatedProjectId() {
		return relatedProjectId;
	}

	public void setRelatedProjectId(Projects relatedProjectId) {
		this.relatedProjectId = relatedProjectId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (issueId != null ? issueId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Issues)) {
			return false;
		}
		Issues other = (Issues) object;
		if ((this.issueId == null && other.issueId != null)
				|| (this.issueId != null && !this.issueId.equals(other.issueId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Issues [issueId=" + issueId + ", issueSummary=" + issueSummary + ", issueDescription="
				+ issueDescription + ", identifiedDate=" + identifiedDate + ", status=" + status + ", priority="
				+ priority + ", targetResolutionDate=" + targetResolutionDate + ", actualResolutionDate="
				+ actualResolutionDate + ", resolutionSummary=" + resolutionSummary + ", createdOn=" + createdOn
				+ ", createdBy=" + createdBy + ", modifiedOn=" + modifiedOn + ", modifiedBy=" + modifiedBy
				+ ", identifiedByPersonId=" + identifiedByPersonId + ", assignedToPersonId=" + assignedToPersonId
				+ ", relatedProjectId=" + relatedProjectId + "]";
	}

}
