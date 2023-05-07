package com.shop.services;

import java.util.List;

import com.shop.struct.Orderitems;

public interface OrderItemsService {

	List<Orderitems> getById(Long orderId);
}
