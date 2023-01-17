package com.fooddelivery.recommendationservice.dto;

import com.fooddelivery.recommendationservice.model.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RestaurantPopularityDto {
	
	Restaurant rest;
	int distinctUsers;
}
