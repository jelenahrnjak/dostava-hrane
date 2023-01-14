package com.fooddelivery.recommendationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewRestaurantDto {
	
	String restId;
	String restName;
	String description;

	double latitude;
	double longitude;


}
