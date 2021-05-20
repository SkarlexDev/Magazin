package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.services.ItemService;
import com.shop.services.UserService;
import com.shop.struct.Item;
import com.shop.struct.User;

import java.util.List;



@RestController
public class TrackController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/users/info")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	@RequestMapping("/users/info/{id}")
	public User getUser(@PathVariable String id) {
		return userService.getEmail(id); //email
	}
	
	@RequestMapping("/items/info")
	public List<Item> getallItems() {
		return itemService.getAllItems();
	}
	
	@RequestMapping("/items/info/{id}")
	public Item getItem(@PathVariable String id) {
		return itemService.getItem(id);
	}
	
	@RequestMapping("/shop/{category}/{id}")
	public Item getItem2(@PathVariable String category,@PathVariable String id) {
		return itemService.getItem(id);
	}
}
