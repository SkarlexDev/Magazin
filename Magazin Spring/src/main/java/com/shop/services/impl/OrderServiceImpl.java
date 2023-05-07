package com.shop.services.impl;

import com.shop.repository.OrderRepository;
import com.shop.services.OrderService;
import com.shop.struct.Order;
import com.shop.struct.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Optional<Order> getOrderId(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order getOrderByOrderID(String orderID) {
        return orderRepository.findByorderID(orderID);
    }

}
