/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.usersservice.service;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
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
	private IssueServiceClient issueServiceClient;
	private BCryptPasswordEncoder encoder;

	@Autowired
	private Environment environment;

	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository, RolesRepository rolesRepository,
			BCryptPasswordEncoder encoder, IssueServiceClient issueServiceClient) {
		this.usersRepository = usersRepository;
		this.rolesRepository = rolesRepository;
		this.encoder = encoder;
		this.issueServiceClient = issueServiceClient;
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

		CreatePersonRequest cpr = new CreatePersonRequest(userEntity.getId(), "", "");
		try {
			issueServiceClient.createPersonForUser(cpr);
		} catch (Exception e) {
			usersRepository.delete(userEntity);
		}
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

	@Override
	public void deleteUser(Integer id) {
		Users userEntity = usersRepository.findById(id).get();
		usersRepository.delete(userEntity);
	}

	@Override
	public Integer getUserIdFromJwt(HttpServletRequest req) {
		String authorizationHeader = req.getHeader("Authorization");

		if (authorizationHeader == null) {
			return null;
		}

		String token = authorizationHeader.replace("Bearer ", "");

		String userId = Jwts.parser().setSigningKey(environment.getProperty("token.secret")).parseClaimsJws(token)
				.getBody().getSubject();

		if (userId == null) {
			return null;
		}

		return Integer.parseInt(userId);
	}

}
