package com.shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.repository.OrderitemsRepository;
import com.shop.struct.Orderitems;

@Service
public class OrderitemsService {

	@Autowired
	private OrderitemsRepository orderitemsRepository;

	public List<Orderitems> getById(int orderId) {
		return orderitemsRepository.findByorderId(orderId);
	}
}
