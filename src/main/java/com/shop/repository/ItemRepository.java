package com.shop.repository;

import org.springframework.data.repository.CrudRepository;

import com.shop.struct.Item;
import java.util.List;


public interface ItemRepository extends CrudRepository<Item, Long>{

		List<Item> findAllByCategory(String category);

		Item findByItemlink(String itemlink);

}
