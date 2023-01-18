package com.fooddelivery.restaurantservice.service;

import java.util.List;

import com.fooddelivery.restaurantservice.dto.NewMealDto;
import com.fooddelivery.restaurantservice.dto.NewRestaurantDto;
import com.fooddelivery.restaurantservice.dto.RestaurantDto;
import com.fooddelivery.restaurantservice.model.Meal;
import com.fooddelivery.restaurantservice.model.Restaurant;

public interface RestaurantService {
	
	Restaurant createRestaurant(NewRestaurantDto newRest);
	RestaurantDto getRestaurant(String restId);
	List<Restaurant> getAll();
	Meal addNewMeal(NewMealDto newMeal);
	
}
