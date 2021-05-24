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
import java.util.Optional;



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
	public Optional<User> getUser(@PathVariable Long id) {
		return userService.getUserId(id);
	}
	
	@RequestMapping("/items/info")
	public List<Item> getallItems() {
		return itemService.findAll();
	}
	
	@RequestMapping("/items/info/{id}")
	public Optional<Item> getItem(@PathVariable Long id) {
		return itemService.findById(id);
	}
	@RequestMapping("/shop/{category}/{itemlink}")
	public Item findById(@PathVariable String category,@PathVariable String itemlink) {

		return itemService.findByLink(itemlink);
	}
}
