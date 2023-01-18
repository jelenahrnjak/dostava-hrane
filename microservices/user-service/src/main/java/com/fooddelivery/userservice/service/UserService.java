package com.fooddelivery.userservice.service;

import com.fooddelivery.userservice.dto.CredentialDTO;
import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.model.User;

public interface UserService {
	 
	User findById(String id);
	UserDto registration(UserDto newUser);
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
	UserDto validateToken(String token);
	UserDto login(CredentialDTO credentialsDto);
}
