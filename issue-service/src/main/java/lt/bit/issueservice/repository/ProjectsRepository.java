package lt.bit.issueservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.bit.issueservice.entity.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Integer>{

	Projects findByProjectName(String projectName);
}
