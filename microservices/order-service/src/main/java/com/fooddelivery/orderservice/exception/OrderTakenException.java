package com.fooddelivery.orderservice.exception;


@SuppressWarnings("serial")
public class OrderTakenException extends RuntimeException{
    public OrderTakenException(String message) {
    	super(message);
    }
}
