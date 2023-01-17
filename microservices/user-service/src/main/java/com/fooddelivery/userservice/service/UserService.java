package com.fooddelivery.userservice.service;

import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.model.User;

public interface UserService {
	
    User register(UserDto newUser);
    User findByUsername(String username);
	User findById(String id);

}
