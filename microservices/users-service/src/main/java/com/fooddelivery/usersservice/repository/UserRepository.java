package com.fooddelivery.usersservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.usersservice.model.User;


public interface UserRepository extends JpaRepository<User, String>{
	
    User findByUsername(String username);

    User findByEmail(String email);
}
