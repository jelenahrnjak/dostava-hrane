package com.fooddelivery.restaurantservice.controller;

import java.util.Collection;
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

import com.fooddelivery.restaurantservice.dto.NewMealDto;
import com.fooddelivery.restaurantservice.dto.NewRestaurantDto;
import com.fooddelivery.restaurantservice.dto.RestaurantDto;
import com.fooddelivery.restaurantservice.model.Meal;
import com.fooddelivery.restaurantservice.model.Restaurant;
import com.fooddelivery.restaurantservice.service.RestaurantService;
import com.fooddelivery.restaurantservice.service.SearchService;

@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService service;
	@Autowired
	SearchService searchService;
	
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
	
	@PostMapping("/meal")
	public ResponseEntity<?> addMealToRestaurant(@RequestBody NewMealDto meal, BindingResult bindingResult) {
		
		try {
			Meal newMeal = service.addNewMeal(meal);
		    return new ResponseEntity<>(newMeal, HttpStatus.CREATED);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}
	    
	}
	
	@GetMapping("/search/name/{name}")
	public ResponseEntity<?> searchByName(@PathVariable("name") String name) {
		
		try {
			List<RestaurantDto> result = searchService.searchByRestaurantName(name);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/search/type/{type}")
	public ResponseEntity<?> searchByType(@PathVariable("type") String type) {
		
		try {
			List<RestaurantDto> result = searchService.searchByRestaurantType(type);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/search/mealname/{name}")
	public ResponseEntity<?> searchByMealName(@PathVariable("name") String name) {
		
		try {
			Collection<RestaurantDto> result = searchService.searchByMealName(name);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/search/mealtype/{type}")
	public ResponseEntity<?> searchByMealType(@PathVariable("type") String type) {
		
		try {
			Collection<RestaurantDto> result = searchService.searchByMealType(type);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		
		try {
			RestaurantDto result = service.getRestaurant(id);
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		
		try {
			List<Restaurant> result = service.getAll();
		    return new ResponseEntity<>(result, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	

}
