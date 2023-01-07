package com.fooddelivery.orderservice.model;


public enum OrderStatus {
    CREATED("CREATED"), DELIVERED("DELIVERED"), CANCELLED("CANCELLED"), DELIVERING("DELIVERING");

    private String orderStatus;

    OrderStatus(String status) {
        this.orderStatus =status;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
