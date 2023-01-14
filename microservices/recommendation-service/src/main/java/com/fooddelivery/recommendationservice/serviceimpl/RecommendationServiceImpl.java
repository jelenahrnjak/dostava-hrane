package com.fooddelivery.recommendationservice.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.recommendationservice.dto.RecentOrderDto;
import com.fooddelivery.recommendationservice.dto.RestaurantPopularityDto;
import com.fooddelivery.recommendationservice.model.Restaurant;
import com.fooddelivery.recommendationservice.repository.OrderRepository;
import com.fooddelivery.recommendationservice.repository.RestaurantRepository;
import com.fooddelivery.recommendationservice.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService{
	
	@Autowired
	RestaurantRepository restRepo;
	@Autowired
	OrderRepository orderRepo;
	
	@Override
	public List<Restaurant> getPopularRestaurants() {
	
		LocalDateTime currentTimestamp = LocalDateTime.now();
		LocalDateTime daysRange = currentTimestamp.minusDays(15);

		List<Restaurant> ret = new ArrayList<>();
		for(RestaurantPopularityDto dto : restRepo.getPopularRestaurants(daysRange.toString(), 10)) 
			ret.add(dto.getRest());

		return ret;
	}
	
	@Override
	public List<Restaurant> getRestaurantsNearBy(String userId) {
		return restRepo.getClosestRestaurantsForUser(userId, 3);
	}
	
	@Override
	public List<Restaurant> getRestaurantsByRecentOrders(String userId) {
		
		List<Restaurant> ret = new ArrayList<>();
		
		for(RecentOrderDto dto: orderRepo.getRecentOrdersForUser("user2", 5))
			ret.add(dto.getRest());
		
		return ret;
	}
	@Override
	public List<Restaurant> getRestaurantRecommendationByType(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	

}
