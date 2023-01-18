package com.fooddelivery.restaurantservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fooddelivery.restaurantservice.model.Restaurant;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String>{
	
	@Query("{ 'name': { '$regex': ?0, '$options': 'i' } }")
	List<Restaurant> findByRestaurantName(String name);
	
	@Query("{'types': { '$in': [ { '$regex': ?0, '$options': 'i' } ] } }")
	List<Restaurant> findByRestaurantType(String type);
	
	Restaurant findByRestautantId(String id);

}
