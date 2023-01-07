package com.fooddelivery.orderservice.service;

import java.util.List;

import com.fooddelivery.orderservice.dto.NewOrderDto;
import com.fooddelivery.orderservice.model.Order;

public interface OrderService {
	
	Order createOrder(NewOrderDto newOrder);

	Order takeOrder(String orderId, String delivererId);
	Order cancelOrder(String orderId);
	Order deliverOrder(String orderId);
	
	List<Order> getOrdersByCustomer(String userId);
	List<Order> getOrdersByDeliverer(String userId);
	List<Order> getActiveOrders();

}
