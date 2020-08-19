/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.usersservice.service;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lt.bit.usersservice.entity.Roles;
import lt.bit.usersservice.entity.Users;
import lt.bit.usersservice.exception.UserAlreadyExistsException;
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
