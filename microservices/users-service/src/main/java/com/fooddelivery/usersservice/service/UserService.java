package com.fooddelivery.usersservice.service;

import com.fooddelivery.usersservice.dto.CredentialDTO;
import com.fooddelivery.usersservice.dto.UserDto;
import com.fooddelivery.usersservice.model.User;

public interface UserService {
	 
	UserDto findById(String id);
	UserDto registration(UserDto newUser);
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
	UserDto validateToken(String token);
	String login(CredentialDTO credentialsDto);
}
