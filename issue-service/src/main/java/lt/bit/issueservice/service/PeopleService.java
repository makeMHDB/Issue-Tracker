package lt.bit.issueservice.service;

import lt.bit.issueservice.model.CreatePersonRequest;
import lt.bit.issueservice.model.UpdatePersonRequest;

public interface PeopleService {

	void createPersonForUser(CreatePersonRequest personDetails);
	void updatePersonInfo(Integer id, UpdatePersonRequest personDetails);
	void deletePerson(Integer id);
}
