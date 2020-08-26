/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.usersservice.service;

import java.util.Collections;
import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lt.bit.usersservice.entity.Roles;
import lt.bit.usersservice.entity.Users;
import lt.bit.usersservice.exception.UserAlreadyExistsException;
import lt.bit.usersservice.model.CreatePersonRequest;
import lt.bit.usersservice.model.UsersRegisterAndLoginRequest;
import lt.bit.usersservice.repository.RolesRepository;
import lt.bit.usersservice.repository.UsersRepository;

/**
 *
 * @author makeMH
 */
@Service
public class UsersServiceImpl implements UsersService {

	private UsersRepository usersRepository;
	private RolesRepository rolesRepository;
	private BCryptPasswordEncoder encoder;

	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository, RolesRepository rolesRepository,
			BCryptPasswordEncoder encoder) {
		this.usersRepository = usersRepository;
		this.rolesRepository = rolesRepository;
		this.encoder = encoder;
	}

	@Override
	public void createUser(UsersRegisterAndLoginRequest userDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Users userEntity = usersRepository.findByUsername(userDetails.getUsername());
		if (userEntity != null) {
			throw new UserAlreadyExistsException(userDetails.getUsername());
		}
		userEntity = modelMapper.map(userDetails, Users.class);
		userEntity.setRolesSet(new HashSet<Roles>());
		userEntity.setEnabled(true);
		userEntity.setPassword(encoder.encode(userDetails.getPassword()));
		Roles role = rolesRepository.findById(1).get();
		userEntity.getRolesSet().add(role);

		usersRepository.save(userEntity);

		String url = "http://localhost:8080/issue-service/people";
		RestTemplate restTemplate = new RestTemplate();

		CreatePersonRequest cpr = new CreatePersonRequest(userEntity.getId(), "Hardcoded Name", "hardcoded@email.com");

		HttpEntity<CreatePersonRequest> request = new HttpEntity<CreatePersonRequest>(cpr);
		ResponseEntity<CreatePersonRequest> response = restTemplate.postForEntity(url, request,
				CreatePersonRequest.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users userEntity = usersRepository.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getAuthorities());
	}

	public Integer getUserId(String username) {
		Users userEntity = usersRepository.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		return userEntity.getId();
	}

}
