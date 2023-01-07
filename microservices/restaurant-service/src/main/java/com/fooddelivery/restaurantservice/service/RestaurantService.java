package com.fooddelivery.restaurantservice.service;

import java.util.List;

import com.fooddelivery.restaurantservice.dto.NewRestaurantDto;
import com.fooddelivery.restaurantservice.model.Restaurant;

public interface RestaurantService {
	
	Restaurant createRestaurant(NewRestaurantDto newRest);
	Restaurant getRestaurant(String restId);

	List<Restaurant> searchByRestaurantName(String name);
	List<Restaurant> searchByRestaurantType(String type);
	
	List<Restaurant> searchByMealName(String name);
	List<Restaurant> searchByMealType(String type);
	
}
