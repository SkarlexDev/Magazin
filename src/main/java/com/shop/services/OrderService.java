package com.shop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.repository.OrderRepository;
import com.shop.struct.Order;
import com.shop.struct.User;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> getOrdersbyUser(User user) {
		return orderRepository.findByUser(user);
	}

	public Optional<Order> getOrderId(long id) {
		return orderRepository.findById(id);
	}
	
	public Order getOrderByorderID(String orderID)
	{
		return orderRepository.findByorderID(orderID);
	}
}
