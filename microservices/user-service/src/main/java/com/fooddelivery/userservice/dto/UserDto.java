package com.fooddelivery.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String dateOfBirth;

    private String username;
	private String role;
    
	private long longitude;
	
	private long latitude;

    private String token;

    private String password;
}
