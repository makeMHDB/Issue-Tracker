package lt.bit.issueservice.service;

import lt.bit.issueservice.model.CreatePersonRequest;

public interface PeopleService {

	void createPersonForUser(CreatePersonRequest personDetails);
}
