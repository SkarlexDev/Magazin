package com.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.shop.struct.Orderitems;

public interface OrderitemsRepository extends CrudRepository<Orderitems, Long> {

	public List<Orderitems> findByorderId(Long orderId);
}
