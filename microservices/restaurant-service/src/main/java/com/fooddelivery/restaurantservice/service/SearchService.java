package com.fooddelivery.restaurantservice.service;

import java.util.List;

import com.fooddelivery.restaurantservice.model.Restaurant;

public interface SearchService {

	List<Restaurant> searchByRestaurantName(String name);
	List<Restaurant> searchByRestaurantType(String type);
	
	List<Restaurant> searchByMealName(String name);
	List<Restaurant> searchByMealType(String type);
}
