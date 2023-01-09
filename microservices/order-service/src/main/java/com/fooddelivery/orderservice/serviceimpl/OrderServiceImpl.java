package com.fooddelivery.orderservice.serviceimpl;

import java.time.Clock;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.orderservice.dto.NewOrderDto;
import com.fooddelivery.orderservice.exception.OrderCancelledException;
import com.fooddelivery.orderservice.exception.OrderDeliveryStartedException;
import com.fooddelivery.orderservice.exception.OrderTakenException;
import com.fooddelivery.orderservice.model.Order;
import com.fooddelivery.orderservice.model.OrderStatus;
import com.fooddelivery.orderservice.repository.OrderRepository;
import com.fooddelivery.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Order createOrder(NewOrderDto newOrder) {
        Order order = new Order(newOrder);
        
        Clock clock = Clock.systemDefaultZone();
        Date currentDate = Date.from(clock.instant());
        
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderStatus(OrderStatus.CREATED);
        order.setDelivererId("none");
        order.setCreationDate(currentDate);
        return orderRepository.insert(order);
	}

	@Override
	public Order takeOrder(String orderId, String delivererId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if(!order.get().getDelivererId().equals("none")) {
			throw new OrderTakenException(
					"Order has already been taken by another deliverer.");
		}
		if(order.get().getOrderStatus().equals(OrderStatus.CANCELLED)) {
			throw new OrderCancelledException(
					"Sorry, the order has been cancelled by customer.");
		}
		order.get().setDelivererId(delivererId);
		order.get().setOrderStatus(OrderStatus.DELIVERING);
		return orderRepository.save(order.get());
	}
	
	@Override
	public Order cancelOrder(String orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if(!order.get().getOrderStatus().equals(OrderStatus.CREATED)) {
			throw new OrderDeliveryStartedException(
					"Order cannot be cancelled, because delivery has already started");
		};
		order.get().setOrderStatus(OrderStatus.CANCELLED);
		return orderRepository.save(order.get());
	}

	@Override
	public Order deliverOrder(String orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		order.get().setOrderStatus(OrderStatus.DELIVERED);
		return orderRepository.save(order.get());
	}

	@Override
	public List<Order> getOrdersByCustomer(String userId) {
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getOrdersByDeliverer(String userId) {
		return orderRepository.findByDelivererId(userId);
	}

	@Override
	public List<Order> getActiveOrders() {
		return orderRepository.findByOrderStatus("CREATED");
	}



}
