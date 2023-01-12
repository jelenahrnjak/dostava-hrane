package com.fooddelivery.userservice.model;
import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.model.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
	
    public User(UserDto newUser) {
		this.firstName=newUser.getFirstName();
		this.lastName=newUser.getLastName();
		this.email=newUser.getEmail();
		this.phoneNumber=newUser.getPhoneNumber();
		this.dateOfBirth=newUser.getDateOfBirth();
		this.username=newUser.getUsername();
	}

	@Id
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "longitude", unique = false, nullable = false)
    private long longitude;
    
    @Column(name = "latitude", unique = false, nullable = false)
    private long latitude;

    @Column(name = "role", unique = false, nullable = false)
    private Role role;

}
