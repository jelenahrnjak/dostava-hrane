package com.fooddelivery.restaurantservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fooddelivery.restaurantservice.dto.NewRestaurantDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "restaurants")
public class Restaurant {
	
	@Id
	String restautantId;
	
	String name;
	String description;
	String image;
	Address address;
	
	List<String> types;
	
	public Restaurant(NewRestaurantDto newRest) {
		this.name = newRest.getName();
		this.description = newRest.getDescription();
		this.image = newRest.getImage();
		this.types = newRest.getTypes();
	}
	

}
