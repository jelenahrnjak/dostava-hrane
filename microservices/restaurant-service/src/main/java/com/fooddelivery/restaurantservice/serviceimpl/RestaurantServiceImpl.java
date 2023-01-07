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

	@Override
	public List<Restaurant> searchByRestaurantName(String name) {
		return repo.findByRestaurantName(name);
	}

	@Override
	public List<Restaurant> searchByRestaurantType(String type) {
		return repo.findByRestaurantType(type);
	}

	@Override
	public List<Restaurant> searchByMealName(String name) {
		
		List<Restaurant> all= repo.findAll();
		List<Restaurant> result = new ArrayList<>();
		
		for(Restaurant r: all) {
			List<Meal> meals = r.getMeals();
			for(Meal m: meals) {
				if(m.getName().toUpperCase().contains(name.toUpperCase())) result.add(r);
			}
		}
		return result;
	}

	@Override
	public List<Restaurant> searchByMealType(String type) {
		List<Restaurant> all= repo.findAll();
		List<Restaurant> result = new ArrayList<>();
		
		for(Restaurant r: all) {
			List<Meal> meals = r.getMeals();
			for(Meal m: meals) {
				if(m.getMealType().toUpperCase().contains(type.toUpperCase())) result.add(r);
			}
		}
		return result;
	}

}
