package lt.bit.issueservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.bit.issueservice.entity.People;
import lt.bit.issueservice.entity.Projects;
import lt.bit.issueservice.exception.ProjectAlreadyExistsException;
import lt.bit.issueservice.exception.ProjectNotFoundException;
import lt.bit.issueservice.model.CreateProjectRequest;
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
		Integer userId = usersServiceClient.getUserIdFromJwt(req.getHeader("Authorization"));
		People personEntity = peopleRepository.findByUserId(userId);

		projectEntity = modelMapper.map(projectDetails, Projects.class);
		projectEntity.setStartDate(new Date());
		projectEntity.setCreatedBy(personEntity.getPersonName());
		projectEntity.setCreatedOn(new Date());
		projectEntity.setModifiedBy(personEntity.getPersonName());
		projectEntity.setModifiedOn(new Date());
		projectsRepository.save(projectEntity);
	}

	@Override
	public void updateProject(CreateProjectRequest projectDetails, Integer id, HttpServletRequest req) {
		Optional<Projects> projectEntityOptional = projectsRepository.findById(id);
		if (!projectEntityOptional.isPresent()) {
			throw new ProjectNotFoundException(id.toString());
		}
		Integer userId = usersServiceClient.getUserIdFromJwt(req.getHeader("Authorization"));
		People personEntity = peopleRepository.findByUserId(userId);

		Projects projectEntity = projectEntityOptional.get();
		projectEntity.setProjectName(projectDetails.getProjectName());
		projectEntity.setTargetEndDate(projectDetails.getTargetEndDate());
		projectEntity.setModifiedOn(new Date());
		projectEntity.setModifiedBy(personEntity.getPersonName());
		projectsRepository.save(projectEntity);

	}

	@Override
	public void deleteProject(Integer id) {
		Optional<Projects> projectEntityOptional = projectsRepository.findById(id);
		if (!projectEntityOptional.isPresent()) {
			throw new ProjectNotFoundException(id.toString());
		}

		projectsRepository.delete(projectEntityOptional.get());

	}

	@Override
	public void closeProject(Integer id, HttpServletRequest req) {
		Optional<Projects> projectEntityOptional = projectsRepository.findById(id);
		if (!projectEntityOptional.isPresent()) {
			throw new ProjectNotFoundException(id.toString());
		}
		Integer userId = usersServiceClient.getUserIdFromJwt(req.getHeader("Authorization"));
		People personEntity = peopleRepository.findByUserId(userId);

		Projects projectEntity = projectEntityOptional.get();
		projectEntity.setActualEndDate(new Date());
		projectEntity.setModifiedOn(new Date());
		projectEntity.setModifiedBy(personEntity.getPersonName());
		projectsRepository.save(projectEntity);
	}

}
