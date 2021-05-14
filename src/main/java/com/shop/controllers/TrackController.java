package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.items.Item;
import com.shop.items.ItemService;
import com.shop.user.User;
import com.shop.user.UserService;

import java.util.List;



@RestController
public class TrackController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/users/info")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	@RequestMapping(value="/users/info/{id}")
	public User getUser(@PathVariable String id) {
		return userService.getEmail(id); //email
	}
	
	@RequestMapping(value="/items/info")
	public List<Item> getallItems() {
		return itemService.getAllItems();
	}
	
	@RequestMapping(value="/items/info/{id}")
	public Item getItem(@PathVariable String id) {
		return itemService.getItem(id);
	}
}
