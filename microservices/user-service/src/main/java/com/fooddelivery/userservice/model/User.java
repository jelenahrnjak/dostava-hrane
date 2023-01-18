package com.fooddelivery.userservice.model;
import com.fooddelivery.userservice.dto.UserDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
		this.address = newUser.getAddress();
		this.longitude = newUser.getLongitude();
		this.latitude = newUser.getLatitude();
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

    @Column(name = "password", unique = false, nullable= true)
    private String password;
    
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Address address;
    
    private String address;
    
    private long latitude;
    private long longitude;

    @Column(name = "role", unique = false, nullable = false)
    private String role;
}
