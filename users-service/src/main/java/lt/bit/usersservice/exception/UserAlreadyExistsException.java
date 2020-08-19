/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.usersservice.exception;

/**
 *
 * @author makeMH
 */
public class UserAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 546394146652013592L;

	public UserAlreadyExistsException(String message) {
        super("User " + message + " already exists!");
    }
    
    
    
}
