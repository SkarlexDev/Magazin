package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.struct.Orderitems;

public interface OrderitemsRepository extends JpaRepository<Orderitems, Long> {

    List<Orderitems> findByorderId(Long orderId);
}
