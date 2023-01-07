package com.fooddelivery.userservice.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.model.User;
import com.fooddelivery.userservice.service.UserService;
import com.fooddelivery.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
    @Autowired
    private UserRepository userRepository;
	
	@Override
	public User register(UserDto newUser) {
		
		User user=new User(newUser);
		user.setId(UUID.randomUUID().toString());
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
