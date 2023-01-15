package com.fooddelivery.userservice.model;
import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.model.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
		if(newUser.getRole() == "USER") {
			this.role = Role.USER;
		}else {
			this.role = Role.DELIVERER;
		} 
		
		this.longitude = newUser.getLongitude();
		this.latitude = newUser.getLatitude();
	}


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user_generator")
    @GenericGenerator(
            name = "sequence_user_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_user"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;
     
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", unique = false, nullable= true)
    private String password;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "date_of_birth")
    private String dateOfBirth;


    @Column(name = "longitude", unique = false, nullable = false)
    private long longitude;
    
    @Column(name = "latitude", unique = false, nullable = false)
    private long latitude;

    @Column(name = "role", unique = false, nullable = false)
    private Role role;
 
}
