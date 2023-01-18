package com.fooddelivery.restaurantservice.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.restaurantservice.dto.RestaurantDto;
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
	public Collection<RestaurantDto> searchByMealName(String name) {
		List<Meal> meals = mealRepo.findByMealName(name);
		Map<String, Restaurant> result = new HashMap<>();

		for(Meal m: meals) {
			
			try {
			Restaurant rest = restRepo.findByRestautantId(m.getRestautantId());	
			if(!result.containsKey(rest.getRestautantId())) result.put(rest.getRestautantId(), rest);
			}
			catch(Exception e) {
				continue;
			}
		}
		
		return collectMeals(result.values());
	}

	@Override
	public Collection<RestaurantDto> searchByMealType(String type) {
		List<Meal> meals = mealRepo.findByMealType(type);
		Map<String, Restaurant> result = new HashMap<>();

		for(Meal m: meals) {
			
			try {
			Restaurant rest = restRepo.findByRestautantId(m.getRestautantId());	
			if(!result.containsKey(rest.getRestautantId())) result.put(rest.getRestautantId(), rest);
			}
			catch(Exception e) {
				continue;
			}
		}
		
		return collectMeals(result.values());
	}
		
	@Override
	public List<RestaurantDto> searchByRestaurantName(String name) {
		
		return collectMeals(restRepo.findByRestaurantName(name));
	}

	@Override
	public List<RestaurantDto> searchByRestaurantType(String type) {
		
		return collectMeals(restRepo.findByRestaurantType(type));
	}
	
	List<RestaurantDto> collectMeals(List<Restaurant> rests){
		List<RestaurantDto> result = new ArrayList<>();
		
		for (Restaurant r : rests) {
			RestaurantDto dto = new RestaurantDto(r);
			dto.setMeals(mealRepo.findAllByRestautantId(r.getRestautantId()));
			result.add(dto);
		}
		return result;		
	}
	
	List<RestaurantDto> collectMeals(Collection<Restaurant> rests){
		List<RestaurantDto> result = new ArrayList<>();
		
		for (Restaurant r : rests) {
			RestaurantDto dto = new RestaurantDto(r);
			dto.setMeals(mealRepo.findAllByRestautantId(r.getRestautantId()));
			result.add(dto);
		}
		return result;		
	}
}
