package lt.bit.issueservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.bit.issueservice.model.CreateProjectRequest;
import lt.bit.issueservice.service.ProjectsService;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

	private ProjectsService projectsService;

	@Autowired
	public ProjectsController(ProjectsService projectsService) {
		this.projectsService = projectsService;
	}

	@PostMapping
	public ResponseEntity createNewProject(@RequestBody CreateProjectRequest projectDetails, HttpServletRequest req) {
		projectsService.createNewProject(projectDetails, req);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateProject(@PathVariable Integer id, @RequestBody CreateProjectRequest projectDetails,
			HttpServletRequest req) {
		projectsService.updateProject(projectDetails, id, req);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteProject(@PathVariable Integer id) {
		projectsService.deleteProject(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}/close")
	public ResponseEntity closeProject(@PathVariable Integer id, HttpServletRequest req) {
		projectsService.closeProject(id, req);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
