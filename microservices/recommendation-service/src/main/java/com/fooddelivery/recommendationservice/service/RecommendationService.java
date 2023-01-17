package com.fooddelivery.recommendationservice.service;

import java.util.List;

import com.fooddelivery.recommendationservice.model.Order;
import com.fooddelivery.recommendationservice.model.Restaurant;

public interface RecommendationService {

	List<Restaurant> getPopularRestaurants();
	List<Restaurant> getRestaurantsNearBy(String userId);
	List<Restaurant> getRestaurantsByRecentOrders(String userId);
	List<Restaurant> getRestaurantRecommendationByType(String userId); 
}
