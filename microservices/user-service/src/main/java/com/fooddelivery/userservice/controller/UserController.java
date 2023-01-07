package com.fooddelivery.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.model.User;
import com.fooddelivery.userservice.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> resgisterUser(@RequestBody UserDto user, BindingResult bindingResult) {
	    User userExists = userService.findByUsername(user.getUsername());
	    if (userExists != null) {
	    	return new ResponseEntity<>("There is already a user registered with the username provided", HttpStatus.BAD_REQUEST);	   
	    }
	    else {
	        userService.register(user);
	        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
	    }
	}





}
