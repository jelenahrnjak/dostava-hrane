package com.fooddelivery.restaurantservice.serviceimpl;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import com.fooddelivery.restaurantservice.dto.NewMealDto;
import com.fooddelivery.restaurantservice.dto.NewRestaurantDto;
import com.fooddelivery.restaurantservice.dto.RestaurantDto;
import com.fooddelivery.restaurantservice.model.Meal;
import com.fooddelivery.restaurantservice.model.Restaurant;
import com.fooddelivery.restaurantservice.repository.MealRepository;
import com.fooddelivery.restaurantservice.repository.RestaurantRepository;
import com.fooddelivery.restaurantservice.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	RestaurantRepository repo;
	
	@Autowired
	MealRepository mealRepo;
	
	@Override
	public Restaurant createRestaurant(NewRestaurantDto newRest) {
		Restaurant restaurant = new Restaurant(newRest);
		restaurant.setRestautantId(UUID.randomUUID().toString());
		return repo.save(restaurant);
	}

	@Override
	public RestaurantDto getRestaurant(String restId) {
		RestaurantDto rest = new RestaurantDto(repo.findByRestautantId(restId));
		rest.setMeals(mealRepo.findAllByRestautantId(restId));
		return rest;
	}

	@Override
	public List<Restaurant> getAll() {
		List<Restaurant> rest = repo.findAll();
		return rest;
	}

	@Override
	public Meal addNewMeal(NewMealDto newMeal) {
		
		Meal meal = new Meal(UUID.randomUUID().toString(),
						newMeal.getRestId(),
						newMeal.getMealName(),
						newMeal.getDescription(),
						newMeal.getMealType(),
						newMeal.getMealPrice());
		
		mealRepo.save(meal);
		return meal;
	}
	
	@Id
	String mealId;
	String restautantId;
	
	String name;
	String description;
	String mealType;
	Double price;
	

}
