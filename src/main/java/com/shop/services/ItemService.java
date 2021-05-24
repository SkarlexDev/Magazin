package com.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.repository.ItemRepository;
import com.shop.struct.Item;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> findAll() {
		List<Item> items = new ArrayList<>();
		itemRepository.findAll()
		.forEach(items::add);
		return items;
	}

	public Optional<Item> findById(Long id) {
		return itemRepository.findById(id);
	}
	
	public Item findByLink(String itemlink) {
		return itemRepository.findByItemlink(itemlink);
	}


	public List<Item> findAllByCategory(String category) {
		List<Item> items = new ArrayList<>();
		itemRepository.findAllByCategory(category)
		.forEach(items::add);
		return items;
		
	}
	public void addItem(Item item) {
		itemRepository.save(item);
	}
	
	public void editItem(Item item) {
		itemRepository.save(item);
	}
	
	public void deleteItem(Long id) {
		itemRepository.deleteById(id);			  
	}



}
