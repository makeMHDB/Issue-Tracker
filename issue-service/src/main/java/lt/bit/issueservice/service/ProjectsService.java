package lt.bit.issueservice.service;

import javax.servlet.http.HttpServletRequest;

import lt.bit.issueservice.model.CreateProjectRequest;

public interface ProjectsService {

	void createNewProject(CreateProjectRequest projectDetails, HttpServletRequest req);
}
