package com.fooddelivery.userservice.service;

import com.fooddelivery.userservice.dto.CredentialsDto;
import com.fooddelivery.userservice.dto.UserDto; 

public interface UserService {
	
	UserDto registration(UserDto newUser);
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
	UserDto validateToken(String token);
	UserDto login(CredentialsDto credentialsDto);

}
