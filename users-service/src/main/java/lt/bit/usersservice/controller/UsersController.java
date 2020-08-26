/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.usersservice.controller;

import lt.bit.usersservice.model.UsersRegisterAndLoginRequest;
import lt.bit.usersservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author makeMH
 */
@RestController
@RequestMapping("/")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody UsersRegisterAndLoginRequest userDetails) {
		usersService.createUser(userDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body("User is created");
	}

}
