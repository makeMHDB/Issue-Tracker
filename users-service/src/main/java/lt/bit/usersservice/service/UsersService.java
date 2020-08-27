/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.usersservice.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

import lt.bit.usersservice.model.UsersRegisterAndLoginRequest;

/**
 *
 * @author makeMH
 */
public interface UsersService extends UserDetailsService {

	void createUser(UsersRegisterAndLoginRequest userDetails);
	void deleteUser(Integer id);
	Integer getUserIdFromJwt(HttpServletRequest req);
	Integer getUserId(String username);

}
