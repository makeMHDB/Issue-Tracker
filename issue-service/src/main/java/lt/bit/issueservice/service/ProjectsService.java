package lt.bit.issueservice.service;

import javax.servlet.http.HttpServletRequest;

import lt.bit.issueservice.model.CreateProjectRequest;

public interface ProjectsService {

	void createNewProject(CreateProjectRequest projectDetails, HttpServletRequest req);

	void updateProject(CreateProjectRequest projectDetails, Integer id, HttpServletRequest req);

	void deleteProject(Integer id);
	
	void closeProject(Integer id, HttpServletRequest req);
}
