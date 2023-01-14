package com.fooddelivery.recommendationservice.model;


import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Restaurant {
	
	@Id
	private String restId;
	
	private String name;
	private String description;
	
	private double longitude;
	private double latitude;

}
