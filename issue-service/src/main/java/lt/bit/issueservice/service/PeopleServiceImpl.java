package lt.bit.issueservice.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.bit.issueservice.entity.People;
import lt.bit.issueservice.exception.UserAlreadyExistsException;
import lt.bit.issueservice.exception.UserNotFoundException;
import lt.bit.issueservice.model.CreatePersonRequest;
import lt.bit.issueservice.model.UpdatePersonRequest;
import lt.bit.issueservice.repository.PeopleRepository;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	PeopleRepository peopleRepository;

	@Override
	public void createPersonForUser(CreatePersonRequest personDetails) {
		People personEntity = peopleRepository.findByUserId(personDetails.getUserId());
		if (personEntity != null) {
			throw new UserAlreadyExistsException();
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		personEntity = modelMapper.map(personDetails, People.class);
		peopleRepository.save(personEntity);
	}

	@Override
	public void updatePersonInfo(Integer id, UpdatePersonRequest personDetails) {
		People personEntity = peopleRepository.findByUserId(id);
		if (personEntity == null) {
			throw new UserNotFoundException(id.toString());
		}
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		personEntity = modelMapper.map(personDetails, People.class);
		peopleRepository.save(personEntity);

	}

}
