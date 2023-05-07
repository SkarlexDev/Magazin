package com.shop.services.impl;

import com.shop.repository.OrderitemsRepository;
import com.shop.services.OrderItemsService;
import com.shop.struct.Orderitems;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderitemsRepository orderitemsRepository;

    public OrderItemsServiceImpl(OrderitemsRepository orderitemsRepository) {
        this.orderitemsRepository = orderitemsRepository;
    }

    @Override
    public List<Orderitems> getById(Long orderId) {
        return orderitemsRepository.findByorderId(orderId);
    }

}
