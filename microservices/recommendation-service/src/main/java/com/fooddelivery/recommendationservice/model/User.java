package com.fooddelivery.recommendationservice.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class User {
	
	@Id
	String userId;
	
	double latitude;
	double longitude;
	
}
