package com.dbs.backend.controller;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.backend.bean.AuthenticationBean;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
@RestController
@RequestMapping("/api/v1")
public class BasicAuthController {

	@GetMapping(path = "/basicauth")
	public AuthenticationBean helloWorldBean() {
		return new AuthenticationBean("You are authenticated");
	}	
	
//    @RequestMapping("/login")
//    public boolean login(@RequestBody User user) {
//        return
//          user.getName().equals("user") && user.getPassword().equals("password");
//    }
//     
//    @RequestMapping("/user")
//    public Principal user(HttpServletRequest request) {
//        String authToken = request.getHeader("Authorization")
//          .substring("Basic".length()).trim();
//        return () ->  new String(Base64.getDecoder()
//          .decode(authToken)).split(":")[0];
//    }
	
	  @RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }
	  
	  
	  
}