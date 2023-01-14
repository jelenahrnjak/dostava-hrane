package com.fooddelivery.recommendationservice.serviceimpl;

import java.time.Clock;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.recommendationservice.dto.NewRestaurantDto;
import com.fooddelivery.recommendationservice.dto.NewUserDto;
import com.fooddelivery.recommendationservice.dto.OrderDto;
import com.fooddelivery.recommendationservice.dto.RestaurantTypeDto;
import com.fooddelivery.recommendationservice.model.Order;
import com.fooddelivery.recommendationservice.model.Restaurant;
import com.fooddelivery.recommendationservice.model.RestaurantType;
import com.fooddelivery.recommendationservice.model.User;
import com.fooddelivery.recommendationservice.repository.OrderRepository;
import com.fooddelivery.recommendationservice.repository.RestaurantRepository;
import com.fooddelivery.recommendationservice.repository.RestaurantTypeRepository;
import com.fooddelivery.recommendationservice.repository.UserRepository;

@Service
public class InsertService {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	RestaurantRepository restRepo;
	@Autowired
	RestaurantTypeRepository restTypeRepo;
	@Autowired
	OrderRepository orderRepo;

	public User insertUserNode(NewUserDto newUserDto) {
		
		User newUser = new User();
		newUser.setUserId(newUserDto.getUserId());
		newUser.setLatitude(newUserDto.getLatitude());
		newUser.setLongitude(newUserDto.getLongitude());
		
		userRepo.save(newUser);
		userRepo.createDistanceRelationship(newUser.getUserId());

		return newUser;
	}
	
	public Restaurant insertRestaurantNode(NewRestaurantDto newRestDto) {
		
		Restaurant newRest = new Restaurant();
		newRest.setRestId(newRestDto.getRestId());
		newRest.setName(newRestDto.getRestName());
		newRest.setDescription(newRest.getDescription());
		newRest.setLatitude(newRestDto.getLatitude());
		newRest.setLongitude(newRestDto.getLongitude());
		
		restRepo.save(newRest);
		restRepo.createDistanceRelationship(newRest.getRestId());
		return newRest;
	}
	
	public RestaurantType insertRestaurantTypeNode(String restType) {
		
		RestaurantType newRestType = new RestaurantType();
		newRestType.setType(restType);		
		return restTypeRepo.save(newRestType);
	}
	
	public void insertRestaurantTypeConnection(RestaurantTypeDto dto) {
		
		restTypeRepo.createTypeRelationship(dto.getRestId(), dto.getType());
	}
	
	public List<Order> insertNewOrder(OrderDto newOrder) {
		
				
		try {
		User user = userRepo.findByUserId(newOrder.getUserId());
		Restaurant rest = restRepo.findById(newOrder.getRestId()).get();
		
		System.out.println("Dobavljeni user je " + user.getUserId());
		System.out.println("Dobavljenirest je " + rest.getRestId());
		
		
        Clock clock = Clock.systemDefaultZone();
        Date currentDate = Date.from(clock.instant());
		
		Order order = new Order();
		order.setOrderId("7");
		order.setUser(user);
		order.setRestaurant(rest);
		order.setOrderDate(currentDate);
		
		orderRepo.save(order);
		
		List<Order> ret = orderRepo.findAllByStartNode(user.getUserId());
		return ret;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
}
