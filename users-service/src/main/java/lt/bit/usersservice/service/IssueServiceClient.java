package lt.bit.usersservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lt.bit.usersservice.model.CreatePersonRequest;

@FeignClient(name = "issue-service")
public interface IssueServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/people")
	void createPersonForUser(CreatePersonRequest personDetails);

}
