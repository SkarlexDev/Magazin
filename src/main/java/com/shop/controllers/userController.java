package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.user.user;
import com.shop.user.userService;

import java.util.List;



@RestController
public class userController {
	
	@Autowired
	private userService userService;
	@RequestMapping(value="/users")
	public List<user> getAllUsers() {
		return userService.getAllUsers();
	}
}
