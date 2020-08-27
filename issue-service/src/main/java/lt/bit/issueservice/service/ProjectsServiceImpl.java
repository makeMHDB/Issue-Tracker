package lt.bit.issueservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.bit.issueservice.entity.People;
import lt.bit.issueservice.entity.Projects;
import lt.bit.issueservice.exception.ProjectAlreadyExistsException;
import lt.bit.issueservice.model.CreateProjectRequest;
import lt.bit.issueservice.model.GetUserIdResponse;
import lt.bit.issueservice.repository.PeopleRepository;
import lt.bit.issueservice.repository.ProjectsRepository;

@Service
public class ProjectsServiceImpl implements ProjectsService {

	private ProjectsRepository projectsRepository;
	private PeopleRepository peopleRepository;
	private UsersServiceClient usersServiceClient;
	private SimpleDateFormat sdf;
	private ModelMapper modelMapper;

	@Autowired
	public ProjectsServiceImpl(ProjectsRepository projectsRepository, PeopleRepository peopleRepository,
			UsersServiceClient usersServiceClient, SimpleDateFormat sdf, ModelMapper modelMapper) {
		this.projectsRepository = projectsRepository;
		this.peopleRepository = peopleRepository;
		this.usersServiceClient = usersServiceClient;
		this.sdf = sdf;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createNewProject(CreateProjectRequest projectDetails, HttpServletRequest req) {
		Projects projectEntity = projectsRepository.findByProjectName(projectDetails.getProjectName());
		if (projectEntity != null) {
			throw new ProjectAlreadyExistsException();
		}
		String userIdResponse = usersServiceClient.getUserIdFromJwt(req.getHeader("Authorization"));
		People personEntity = peopleRepository.findByUserId(Integer.parseInt(userIdResponse));

		projectEntity = modelMapper.map(projectDetails, Projects.class);
		projectEntity.setStartDate(new Date());
		projectEntity.setCreatedBy(personEntity.getPersonName());
		projectEntity.setCreatedOn(new Date());
		projectEntity.setModifiedBy(personEntity.getPersonName());
		projectEntity.setModifiedOn(new Date());
		projectsRepository.save(projectEntity);
	}

}
