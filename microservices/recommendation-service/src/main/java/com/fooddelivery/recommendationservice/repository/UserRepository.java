package com.fooddelivery.recommendationservice.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.fooddelivery.recommendationservice.model.User;

public interface UserRepository extends Neo4jRepository<User,String>{
	
	User findByUserId(String userId);
	
	
	@Query("match (u:User {userId:$0}) match (r:Restaurant) create (u)-[rel: DISTANCE {value: point.distance(point({latitude: u.latitude, longitude: u.longitude}),point({latitude: r.latitude, longitude: r.longitude}))}]->(r)")
	void createDistanceRelationship(String userId);
	
	
}
