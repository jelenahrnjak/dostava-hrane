package com.fooddelivery.restaurantservice.service;

import com.fooddelivery.restaurantservice.dto.NewRestaurantDto;
import com.fooddelivery.restaurantservice.model.Restaurant;

public interface RestaurantService {
	
	Restaurant createRestaurant(NewRestaurantDto newRest);
	Restaurant getRestaurant(String restId);
	
}
