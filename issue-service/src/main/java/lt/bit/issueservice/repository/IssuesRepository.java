package lt.bit.issueservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.bit.issueservice.entity.Issues;
import lt.bit.issueservice.entity.Projects;

public interface IssuesRepository extends JpaRepository<Issues, Integer> {

	Issues findByIssueSummaryAndProject(String issueSummary, Projects relatedProjectId);

}
