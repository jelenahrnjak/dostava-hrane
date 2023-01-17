package com.fooddelivery.recommendationservice.repository;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import com.fooddelivery.recommendationservice.model.RestaurantType;

public interface RestaurantTypeRepository  extends Neo4jRepository<RestaurantType,String>{

	@Query("MATCH (a:Restaurant {restId:$0}), (b:RestaurantType {type:$1})\r\n"
			+ "CREATE (a)-[:IF_OF_TYPE]->(b)")
	void createTypeRelationship(String restId, String restType);
}
