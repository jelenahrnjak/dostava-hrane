package com.fooddelivery.restaurantservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "meals")
public class Meal {
	
	@Id
	String mealId;
	String restautantId;
	
	String name;
	String description;
	String mealType;
	Double price;
	String image;
	
}
