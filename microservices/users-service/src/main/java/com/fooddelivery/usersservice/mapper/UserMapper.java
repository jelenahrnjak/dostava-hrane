package com.fooddelivery.usersservice.mapper;
 
import org.springframework.stereotype.Component;

import com.fooddelivery.usersservice.dto.UserDto;
import com.fooddelivery.usersservice.model.User;
 
  
@Component
public class UserMapper {
  
    public UserDto toUserDtoWithToken(User user, String token) {
    	UserDto dto = new UserDto();
    	dto.setId(user.getId());
    	dto.setUsername(user.getUsername()); 
    	dto.setFirstName(user.getFirstName());
    	dto.setLastName(user.getLastName());
    	dto.setEmail(user.getEmail());
    	dto.setPhoneNumber(user.getPhoneNumber());
    	dto.setAddress(user.getAddress());
    	dto.setLongitude(user.getLongitude());
    	dto.setLatitude(user.getLatitude());
    	dto.setRole(user.getRole());
    	dto.setRole(user.getRole());
    	return dto;
    };
}