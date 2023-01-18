package com.fooddelivery.restaurantservice.service;

import java.util.Collection;
import java.util.List;

import com.fooddelivery.restaurantservice.dto.RestaurantDto;

public interface SearchService {

	List<RestaurantDto> searchByRestaurantName(String name);
	List<RestaurantDto> searchByRestaurantType(String type);
	
	Collection<RestaurantDto> searchByMealName(String name);
	Collection<RestaurantDto> searchByMealType(String type);
}
