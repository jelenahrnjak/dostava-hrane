package com.fooddelivery.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.model.User;
import com.fooddelivery.userservice.service.UserService;

import jakarta.websocket.server.PathParam;

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



	@GetMapping("/{id}")
	public ResponseEntity<?> resgisterUser(@PathVariable("id") String id) {
	    User user = userService.findById(id);
	    if (user == null) {
	    	return new ResponseEntity<>("User does not exists", HttpStatus.BAD_REQUEST);	   
	    }
	    else { 
	        return new ResponseEntity<>(user, HttpStatus.CREATED);
	    }
	}


}
