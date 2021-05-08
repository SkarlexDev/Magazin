package com.shop.items;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ItemService {

	Item item1 = new Item(
			"Televisor 123", 
			"Text lung", 
			1500,
			"url");

	Item item2 = new Item(
			"Televisor 800", 
			"Text lung", 
			150,
			"url");
	private List<Item> items = Arrays.asList(item1, item2);	

	public List<Item> getAllItems() {
		return items;	
	}

	public Item getItem(String id) {
		Item item = items.stream()
				.filter(t -> id.equals(t.getId()))
				.findFirst()
				.orElse(null);

		return item;
	}
}
