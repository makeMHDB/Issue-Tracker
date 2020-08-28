package lt.bit.issueservice.service;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.bit.issueservice.entity.Issues;
import lt.bit.issueservice.entity.People;
import lt.bit.issueservice.entity.Projects;
import lt.bit.issueservice.exception.IssueAlreadyExistsException;
import lt.bit.issueservice.exception.IssueNotFoundException;
import lt.bit.issueservice.exception.ProjectNotFoundException;
import lt.bit.issueservice.exception.UserNotFoundException;
import lt.bit.issueservice.model.CloseIssueRequest;
import lt.bit.issueservice.model.CreateIssueRequest;
import lt.bit.issueservice.model.UpdateIssueRequest;
import lt.bit.issueservice.repository.IssuesRepository;
import lt.bit.issueservice.repository.PeopleRepository;
import lt.bit.issueservice.repository.ProjectsRepository;

@Service
public class IssuesServiceImpl implements IssuesService {

	private IssuesRepository issuesRepository;
	private ProjectsRepository projectsRepository;
	private PeopleRepository peopleRepository;
	private ModelMapper modelMapper;
	private UsersServiceClient usersServiceClient;

	@Autowired
	public IssuesServiceImpl(IssuesRepository issuesRepository, ProjectsRepository projectsRepository,
			PeopleRepository peopleRepository, ModelMapper modelMapper, UsersServiceClient usersServiceClient) {
		this.issuesRepository = issuesRepository;
		this.projectsRepository = projectsRepository;
		this.peopleRepository = peopleRepository;
		this.modelMapper = modelMapper;
		this.usersServiceClient = usersServiceClient;
	}

	@Override
	public void createNewIssue(CreateIssueRequest issueDetails, HttpServletRequest req) {
		Optional<Projects> projectEntityOptional = projectsRepository.findById(issueDetails.getRelatedProjectIdInt());
		if (!projectEntityOptional.isPresent()) {
			throw new ProjectNotFoundException(issueDetails.getRelatedProjectIdInt().toString());
		}
		Projects projectEntity = projectEntityOptional.get();
		Issues issueEntity = issuesRepository.findByIssueSummaryAndProject(issueDetails.getIssueSummary(),
				projectEntity);
		if (issueEntity != null) {
			throw new IssueAlreadyExistsException();
		}
		Integer userId = usersServiceClient.getUserIdFromJwt(req.getHeader("Authorization"));
		People personEntity = peopleRepository.findByUserId(userId);

		issueEntity = modelMapper.map(issueDetails, Issues.class);
		issueEntity.setIdentifiedByPersonId(personEntity);

		// TODO ATM it assigns this issue to the person, who created it
		issueEntity.setAssignedToPersonId(personEntity);

		issueEntity.setRelatedProjectId(projectEntity);
		// Hardcoded?
		issueEntity.setStatus("New issue");
		issueEntity.setCreatedBy(personEntity.getPersonName());
		issueEntity.setCreatedOn(new Date());
		issueEntity.setModifiedBy(personEntity.getPersonName());
		issueEntity.setModifiedOn(new Date());
		issueEntity.setResolutionSummary("");
		System.out.println(issueEntity);

		issuesRepository.save(issueEntity);
	}

	@Override
	public void updateIssue(Integer id, UpdateIssueRequest issueDetails, HttpServletRequest req) {
		Optional<Issues> issueEntityOptional = issuesRepository.findById(id);
		Optional<People> assignedPersonEntityOptional = peopleRepository
				.findById(issueDetails.getAssignedToPersonIdInt());
		if (!issueEntityOptional.isPresent()) {
			throw new IssueNotFoundException(id.toString());
		}
		if (!assignedPersonEntityOptional.isPresent()) {
			throw new UserNotFoundException(issueDetails.getAssignedToPersonIdInt().toString());
		}
		Integer userId = usersServiceClient.getUserIdFromJwt(req.getHeader("Authorization"));
		People personEntity = peopleRepository.findByUserId(userId);
		People assignedPersonEntity = assignedPersonEntityOptional.get();
		Issues issueEntity = issueEntityOptional.get();

		issueEntity.setIssueSummary(issueDetails.getIssueSummary());
		issueEntity.setIssueDescription(issueDetails.getIssueDescription());
		issueEntity.setPriority(issueDetails.getPriority());
		issueEntity.setStatus(issueDetails.getStatus());
		issueEntity.setTargetResolutionDate(issueDetails.getTargetResolutionDate());
		issueEntity.setAssignedToPersonId(assignedPersonEntity);
		issueEntity.setModifiedBy(personEntity.getPersonName());
		issueEntity.setModifiedOn(new Date());

		issuesRepository.save(issueEntity);

	}

	@Override
	public void deleteIssue(Integer id) {
		Optional<Issues> issueEntityOptional = issuesRepository.findById(id);
		if (!issueEntityOptional.isPresent()) {
			throw new IssueNotFoundException(id.toString());
		}

		issuesRepository.delete(issueEntityOptional.get());
	}

	@Override
	public void closeIssue(Integer id, CloseIssueRequest issueDetails, HttpServletRequest req) {
		Optional<Issues> issueEntityOptional = issuesRepository.findById(id);
		if (!issueEntityOptional.isPresent()) {
			throw new IssueNotFoundException(id.toString());
		}
		Integer userId = usersServiceClient.getUserIdFromJwt(req.getHeader("Authorization"));
		People personEntity = peopleRepository.findByUserId(userId);
		Issues issueEntity = issueEntityOptional.get();

		issueEntity.setResolutionSummary(issueDetails.getResolutionSummary());
		issueEntity.setActualResolutionDate(new Date());
		issueEntity.setStatus("Closed");
		issueEntity.setModifiedBy(personEntity.getPersonName());
		issueEntity.setModifiedOn(new Date());

		issuesRepository.save(issueEntity);
	}

}
