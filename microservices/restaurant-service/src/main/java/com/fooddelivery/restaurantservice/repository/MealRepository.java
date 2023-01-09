package com.fooddelivery.restaurantservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fooddelivery.restaurantservice.model.Meal;

@Repository
public interface MealRepository extends MongoRepository<Meal, String>{
	
	@Query("{ 'name': { '$regex': ?0, '$options': 'i' } }")
	List<Meal> findByMealName(String name);
	
	@Query("{ 'mealType': { '$regex': ?0, '$options': 'i' } }")
	List<Meal> findByMealType(String type);
}
