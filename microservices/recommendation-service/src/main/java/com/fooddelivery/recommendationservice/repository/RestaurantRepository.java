package com.fooddelivery.recommendationservice.repository;
import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.fooddelivery.recommendationservice.dto.RestaurantPopularityDto;
import com.fooddelivery.recommendationservice.model.Restaurant;

public interface RestaurantRepository extends Neo4jRepository<Restaurant,String>{
	
	@Query("match (u:User) match (r:Restaurant {restId:$0}) create (u)-[rel: DISTANCE {value: point.distance(point({latitude: u.latitude, longitude: u.longitude}),point({latitude: r.latitude, longitude: r.longitude}))}]->(r)")
	void createDistanceRelationship(String restId);
	
	@Query("MATCH (r:Restaurant)<-[d:DISTANCE]-(u:User {userId: $0}) RETURN r ORDER BY d.value ASC LIMIT $1")
	List<Restaurant> getClosestRestaurantsForUser(String userId, int limit);
	
	@Query("match (rest:Restaurant)<-[r:RESTAURANT]-(o:Order)-[USER]->(u:User) where o.orderDate>$0 return rest, count(distinct u) as distinctUsers order by distinctUsers desc limit $1")
	List<RestaurantPopularityDto> getPopularRestaurants(String timestamp, int limit);
}
