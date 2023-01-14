package com.fooddelivery.recommendationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.recommendationservice.dto.NewRestaurantDto;
import com.fooddelivery.recommendationservice.dto.NewUserDto;
import com.fooddelivery.recommendationservice.dto.OrderDto;
import com.fooddelivery.recommendationservice.dto.RestaurantTypeDto;
import com.fooddelivery.recommendationservice.serviceimpl.InsertService;

@RestController
public class InsertController {
	
	@Autowired
	InsertService insertService;
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody NewUserDto newUser) {
		
		try {
			insertService.insertUserNode(newUser);
		    return new ResponseEntity<>("Node created", HttpStatus.CREATED);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@PostMapping("/restaurant")
	public ResponseEntity<?> createRestaurant(@RequestBody NewRestaurantDto newRest) {
		
		try {
			insertService.insertRestaurantNode(newRest);
		    return new ResponseEntity<>("Node created", HttpStatus.CREATED);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@PostMapping("/type")
	public ResponseEntity<?> createRestaurantType(@RequestBody String restType) {
		
		try {
			insertService.insertRestaurantTypeNode(restType);
		    return new ResponseEntity<>("Node created", HttpStatus.CREATED);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@PostMapping("/restaurantType")
	public ResponseEntity<?> createRestaurantTypeRelationship(@RequestBody RestaurantTypeDto dto) {
		
		try {
			insertService.insertRestaurantTypeConnection(dto);
		    return new ResponseEntity<>("Node created", HttpStatus.CREATED);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@PostMapping("/order")
	public ResponseEntity<?> createOrder(@RequestBody OrderDto newOrder) {
		
		try {
			//service.insertNewOrder(newOrder);
		    return new ResponseEntity<>(insertService.insertNewOrder(newOrder), HttpStatus.CREATED);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}

}
