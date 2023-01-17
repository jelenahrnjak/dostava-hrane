package com.fooddelivery.recommendationservice.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.fooddelivery.recommendationservice.model.Order;
import com.fooddelivery.recommendationservice.model.Restaurant;
import com.fooddelivery.recommendationservice.model.User;

public interface OrderRepository extends Neo4jRepository<Order,String> {
	
	@Query("MATCH (u:User)<-[USER]-(r:Order)-[RESTAURANT]->(Restaurant) WHERE u.userId =$0 RETURN r")
	List<Order> findAllByStartNode(String userId);
	List<Order> findAllByUser(User user);
	
	@Query("MATCH (rest:Restaurant)<-[r:RESTAURANT]-(o:Order)-[USER]->(u:User {userId: $0}) return distinct rest limit $1")
	List<Restaurant> getRecentOrdersForUser(String userId, int limit);
}
