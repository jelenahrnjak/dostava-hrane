package com.fooddelivery.orderservice.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.orderservice.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
	
	List<Order> findByCustomerId(String customerId);
	List<Order> findByDelivererId(String delivererId);
	List<Order> findByOrderStatus(String orderStatus);

}
