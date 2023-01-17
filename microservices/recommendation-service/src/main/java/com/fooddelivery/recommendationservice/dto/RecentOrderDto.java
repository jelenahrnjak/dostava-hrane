package com.fooddelivery.recommendationservice.dto;


import java.sql.Timestamp;

import org.neo4j.ogm.annotation.typeconversion.Convert;

import com.fooddelivery.recommendationservice.model.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RecentOrderDto {
	
	Restaurant rest;
	Object lastOrderDate;
}
