package com.fooddelivery.restaurantservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.restaurantservice.model.Meal;
import com.fooddelivery.restaurantservice.model.Restaurant;
import com.fooddelivery.restaurantservice.repository.MealRepository;
import com.fooddelivery.restaurantservice.repository.RestaurantRepository;
import com.fooddelivery.restaurantservice.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	MealRepository mealRepo;
	@Autowired
	RestaurantRepository restRepo;
	


	@Override
	public List<Restaurant> searchByMealName(String name) {
		
		List<Meal> meals = mealRepo.findByMealName(name);
		List<Restaurant> result = new ArrayList<>();
		
		for(Meal m: meals) {
			
			Restaurant rest = restRepo.findById(m.getRestautantId()).get();			
			if(!result.contains(rest)) result.add(rest);
		}
				
		return result;
	}

	@Override
	public List<Restaurant> searchByMealType(String type) {
		List<Meal> meals = mealRepo.findByMealType(type);
		List<Restaurant> result = new ArrayList<>();
		
		for(Meal m: meals) {
			
			Restaurant rest = restRepo.findById(m.getRestautantId()).get();			
			if(!result.contains(rest)) result.add(rest);
		}
				
		return result;
	}
	
	@Override
	public List<Restaurant> searchByRestaurantName(String name) {
		return restRepo.findByRestaurantName(name);
	}

	@Override
	public List<Restaurant> searchByRestaurantType(String type) {
		return restRepo.findByRestaurantType(type);
	}

}
