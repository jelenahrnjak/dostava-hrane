package com.fooddelivery.restaurantservice.dto;

import java.util.List;

import com.fooddelivery.restaurantservice.model.Address;
import com.fooddelivery.restaurantservice.model.Meal;
import com.fooddelivery.restaurantservice.model.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
	
	String restautantId;
	
	String name;
	String description;
	Address address;
	
	List<String> types;
	List<Meal> meals;

	public RestaurantDto(Restaurant rest) {
		this.restautantId = rest.getRestautantId();
		this.description = rest.getDescription();
		this.name = rest.getName();
		this.types = rest.getTypes();		
	}

}
