package lt.bit.issueservice.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.bit.issueservice.entity.People;
import lt.bit.issueservice.exception.UserAlreadyExistsException;
import lt.bit.issueservice.model.CreatePersonRequest;
import lt.bit.issueservice.repository.PeopleRepository;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	PeopleRepository peopleRepository;

	@Override
	public void createPersonForUser(CreatePersonRequest personDetails) {
		People peopleEntity = peopleRepository.findByUserId(personDetails.getUserId());
		if (peopleEntity != null) {
			throw new UserAlreadyExistsException();
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		peopleEntity = modelMapper.map(personDetails, People.class);
		peopleRepository.save(peopleEntity);
	}

}
