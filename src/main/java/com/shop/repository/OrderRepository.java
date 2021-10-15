package com.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shop.struct.Order;
import com.shop.struct.User;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long>{
	public List<Order> findByUser(User user);
}
