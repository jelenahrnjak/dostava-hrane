package com.fooddelivery.restaurantservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.restaurantservice.dto.NewRestaurantDto;
import com.fooddelivery.restaurantservice.model.Meal;
import com.fooddelivery.restaurantservice.model.Restaurant;
import com.fooddelivery.restaurantservice.repository.RestaurantRepository;
import com.fooddelivery.restaurantservice.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	RestaurantRepository repo;
	
	@Override
	public Restaurant createRestaurant(NewRestaurantDto newRest) {
		Restaurant restaurant = new Restaurant(newRest);
		restaurant.setRestautantId(UUID.randomUUID().toString());
		return repo.save(restaurant);
	}

	@Override
	public Restaurant getRestaurant(String restId) {
		Optional<Restaurant> rest = repo.findById(restId);
		return rest.get();
	}

}
