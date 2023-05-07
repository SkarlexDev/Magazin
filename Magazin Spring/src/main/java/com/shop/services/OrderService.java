package com.shop.services;

import java.util.List;
import java.util.Optional;

import com.shop.struct.Order;
import com.shop.struct.User;

public interface OrderService {

	List<Order> getOrdersByUser(User user);
	Optional<Order> getOrderId(Long id);
	Order getOrderByOrderID(String orderID);
}
