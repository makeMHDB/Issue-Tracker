/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.usersservice.repository;

import lt.bit.usersservice.entity.Users;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author makeMH
 */
public interface UsersRepository extends CrudRepository<Users, Integer> {

	Users findByUsername(String username);

}
