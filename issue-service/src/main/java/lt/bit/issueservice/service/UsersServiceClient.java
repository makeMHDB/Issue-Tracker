package lt.bit.issueservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "users-service")
public interface UsersServiceClient {
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	void deleteUser(@PathVariable Integer id);

}
