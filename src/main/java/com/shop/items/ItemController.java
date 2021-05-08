package com.shop.items;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/items")
	public List<Item> getAllItems() {
		return itemService.getAllItems();	
	}
	
	@RequestMapping(value="items/{id}")
	public Item getItem(@PathVariable String id) {
		return itemService.getItem(id);
	}
}
