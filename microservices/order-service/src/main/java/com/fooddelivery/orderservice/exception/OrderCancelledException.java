package com.fooddelivery.orderservice.exception;

@SuppressWarnings("serial")
public class OrderCancelledException extends RuntimeException{
	public OrderCancelledException(String message) {
		super(message);
	}
}
