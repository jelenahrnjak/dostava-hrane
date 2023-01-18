package com.fooddelivery.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.userservice.dto.CredentialDTO;
import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.model.User;
import com.fooddelivery.userservice.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	 @PostMapping("/login")
	    public ResponseEntity<UserDto> login(@RequestBody CredentialDTO credentialsDto) {
	        //log.info("Trying to login {}", credentialsDto.getUsername());
	        return ResponseEntity.ok(userService.login(credentialsDto));
	    }

	    @PostMapping("/validateToken")
	    public ResponseEntity<UserDto> signIn(@RequestParam String token) {
	        //log.info("Trying to validate token {}", token);
	        return ResponseEntity.ok(userService.validateToken(token));
	    }
	    
	@PostMapping("/signup")
	public ResponseEntity<?> resgisterUser(@RequestBody UserDto user, BindingResult bindingResult) {

	    if(userService.findByEmail(user.getEmail()) != null) {
	    	return new ResponseEntity<>("There is already a user registered with the email provided.", HttpStatus.BAD_REQUEST);	 
		}else if(userService.findByUsername(user.getUsername()) !=null ) {
	    	return new ResponseEntity<>("There is already a user registered with the username provided.", HttpStatus.BAD_REQUEST);	 
		}

	    if (userService.registration(user) == null) {
	    	return new ResponseEntity<>("Something went wrong with registration.", HttpStatus.BAD_REQUEST);	      
	    }
	    else {
	        userService.registration(user);
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
