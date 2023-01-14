package com.fooddelivery.recommendationservice.model;

import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RelationshipEntity(type="ORDER")
public class Order {
	
	@Id
	@GeneratedValue
	private String orderId;
	
	private Date orderDate;
	
	@StartNode
	private User user;
	@EndNode
	private Restaurant restaurant;
	
	
}
