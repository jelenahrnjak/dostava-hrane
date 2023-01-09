package com.fooddelivery.orderservice.exception;


@SuppressWarnings("serial")
public class OrderDeliveryStartedException extends RuntimeException{

	public OrderDeliveryStartedException(String message){
		super(message);
	}
}
