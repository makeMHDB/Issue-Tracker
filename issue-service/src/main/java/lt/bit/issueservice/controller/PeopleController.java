package lt.bit.issueservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.bit.issueservice.model.CreatePersonRequest;
import lt.bit.issueservice.model.UpdatePersonRequest;
import lt.bit.issueservice.service.PeopleService;

@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	PeopleService peopleService;

	@PostMapping
	public ResponseEntity createPersonForUser(@RequestBody CreatePersonRequest personDetails) {
		peopleService.createPersonForUser(personDetails);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity updatePersonInfo(@PathVariable Integer id, @RequestBody UpdatePersonRequest personDetails) {
		peopleService.updatePersonInfo(id, personDetails);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
