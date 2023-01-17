package com.fooddelivery.orderservice.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fooddelivery.orderservice.dto.NewOrderDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
	
	@Id
	String orderId;
	String customerId;
	String delivererId;
	String restaurantId;

	List<OrderItem> orderItems;
	
	Date creationDate;
	Double totalPrice;
	String orderStatus;
	
	public Order(NewOrderDto newOrder) {
		this.restaurantId = newOrder.getRestaurantId();
		this.customerId = newOrder.getCustomerId();
		this.orderItems = newOrder.getOrderItems();
		this.orderStatus = "CREATED";
	}
}
