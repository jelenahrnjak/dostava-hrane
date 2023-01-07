package com.fooddelivery.restaurantservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.restaurantservice.dto.NewRestaurantDto;
import com.fooddelivery.restaurantservice.model.Restaurant;
import com.fooddelivery.restaurantservice.service.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService service;
	
	@PostMapping("/restaurant")
	public ResponseEntity<?> createOrder(@RequestBody NewRestaurantDto rest, BindingResult bindingResult) {
		
		try {
			Restaurant newRest = service.createRestaurant(rest);
		    return new ResponseEntity<>(newRest, HttpStatus.CREATED);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}
	    
	}
	
	@GetMapping("/search/name/{name}")
	public ResponseEntity<?> searchByName(@PathVariable("name") String name) {
		
		try {
			List<Restaurant> result = service.searchByRestaurantName(name);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/search/type/{type}")
	public ResponseEntity<?> searchByType(@PathVariable("type") String type) {
		
		try {
			List<Restaurant> result = service.searchByRestaurantType(type);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}

}
