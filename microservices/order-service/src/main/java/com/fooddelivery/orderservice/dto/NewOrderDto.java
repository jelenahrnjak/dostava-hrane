package com.fooddelivery.orderservice.dto;

import java.util.List;

import com.fooddelivery.orderservice.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderDto {

	String customerId;
	String restaurantId;

	List<OrderItem> orderItems;
}
