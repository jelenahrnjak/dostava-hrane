package com.fooddelivery.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.model.User;

import lombok.RequiredArgsConstructor;
 
@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "token", target = "token")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    @Mapping(source = "user.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "user.longitude", target = "longitude")
    @Mapping(source = "user.latitude", target = "latitude")
    @Mapping(source = "user.role", target = "role")//TODO
    UserDto toUserDtoWithToken(User user, String token);
}