/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.usersservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author makeMH
 */
@RestController
public class HWController {
    
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }
    
}
