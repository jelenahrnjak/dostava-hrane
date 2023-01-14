package com.fooddelivery.recommendationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.recommendationservice.model.Restaurant;
import com.fooddelivery.recommendationservice.service.RecommendationService;

@RestController
public class RecommendationController {

	@Autowired
	RecommendationService service;
	
	@GetMapping("/recent/{id}")
	public ResponseEntity<?> orderAgain(@PathVariable("id") String userId) {
		
		try {
			List<Restaurant> result = service.getRestaurantsByRecentOrders(userId);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/recommendationByType/{id}")
	public ResponseEntity<?> typePrefrences(@PathVariable("id") String userId) {
		
		try {
			List<Restaurant> result = service.getRestaurantRecommendationByType(userId);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/nearBy/{id}")
	public ResponseEntity<?> nearBy(@PathVariable("id") String userId) {
		
		try {
			List<Restaurant> result = service.getRestaurantsNearBy(userId);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/popular")
	public ResponseEntity<?> popularRightNow() {
		
		try {
			List<Restaurant> result = service.getPopularRestaurants();
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	
}
