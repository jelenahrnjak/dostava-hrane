package com.fooddelivery.restaurantservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewRestaurantDto {
	
	String name;
	String description;
	List<String> types;

}
