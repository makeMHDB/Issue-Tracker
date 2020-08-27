/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.usersservice.controller;

import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.bit.usersservice.model.UsersRegisterAndLoginRequest;
import lt.bit.usersservice.service.UsersService;

/**
 *
 * @author makeMH
 */
@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody UsersRegisterAndLoginRequest userDetails) {
		usersService.createUser(userDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body("User is created");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteUser(@PathVariable Integer id) {
		usersService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getUserId")
	public String getUserIdFromJwt(HttpServletRequest req) {
		Integer returnValue = usersService.getUserIdFromJwt(req);
		String ret = String.valueOf(returnValue);
		return ret;
	}

}
