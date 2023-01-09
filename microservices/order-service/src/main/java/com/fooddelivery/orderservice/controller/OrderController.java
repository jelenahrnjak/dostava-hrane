package com.fooddelivery.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.orderservice.dto.NewOrderDto;
import com.fooddelivery.orderservice.exception.OrderDeliveryStartedException;
import com.fooddelivery.orderservice.exception.OrderTakenException;
import com.fooddelivery.orderservice.model.Order;
import com.fooddelivery.orderservice.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<?> createOrder(@RequestBody NewOrderDto order, BindingResult bindingResult) {
		
		try {
			Order orderCreated = orderService.createOrder(order);
		    return new ResponseEntity<>(orderCreated, HttpStatus.CREATED);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}
	    
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> customerOrders(@PathVariable("id") String customerId) {
		
		try {
			List<Order> orders = orderService.getOrdersByCustomer(customerId);
		    return new ResponseEntity<>(orders, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/deliverer/{id}")
	public ResponseEntity<?> delivererOrders(@PathVariable("id") String customerId) {
		
		try {
			List<Order> orders = orderService.getOrdersByDeliverer(customerId);
		    return new ResponseEntity<>(orders, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@GetMapping("/active")
	public ResponseEntity<?> activeOrders() {
		
		try {
			List<Order> orders = orderService.getActiveOrders();
		    return new ResponseEntity<>(orders, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	@PutMapping("/delivering/{orderId}/{delivererId}")
	public ResponseEntity<?> takeOrder(@PathVariable("orderId") String order,@PathVariable("delivererId") String deliverer) {
		
		try {
			Order orderChanged = orderService.takeOrder(order, deliverer);
		    return new ResponseEntity<>(orderChanged, HttpStatus.OK);		
		}
		catch (OrderTakenException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (OrderDeliveryStartedException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/canceling/{orderId}")
	public ResponseEntity<?> cancelOrder(@PathVariable("orderId") String order) {
		
		try {
			Order orderChanged = orderService.cancelOrder(order);
		    return new ResponseEntity<>(orderChanged, HttpStatus.OK);		
		}
		catch (OrderDeliveryStartedException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/delivered/{orderId}")
	public ResponseEntity<?> deliverOrder(@PathVariable("orderId") String order) {
		
		try {
			Order orderChanged = orderService.deliverOrder(order);
		    return new ResponseEntity<>(orderChanged, HttpStatus.OK);		
		}
		catch (Exception e){
			return new ResponseEntity<>("Something went wrong, sorry!", HttpStatus.I_AM_A_TEAPOT);
		}	    
	}
	
	
}
