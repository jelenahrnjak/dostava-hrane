package com.fooddelivery.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.userservice.model.User;


public interface UserRepository extends JpaRepository<User, String>{
	
    User findByUsername(String username);

    User findByEmail(String email);
}
