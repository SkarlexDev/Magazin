package com.shop.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.user.User;
import com.shop.user.UserService;

@Controller
public class RegisterController {
	

	@Autowired
	private UserService userService;
	User user;
	
	@RequestMapping(value="/register")
	public String postUser(Model model) {
		model.addAttribute("userInfo" , new User());
		return "register";	
	}
	
	@PostMapping(value="/register_done")
	public String addUser(@ModelAttribute User userInfo , Model model) {
		System.out.println(userInfo);
		//userService.setUser(userInfo);
		userService.setUser(userInfo);
		return "redirect:users";
	}
	// Temp
	@RequestMapping(value="/users")
	public String niceList(Model model) {
		ArrayList<User> users = userService.getAllUsers();
		model.addAttribute("usersId", users);
		return "users";
	}
	


	
}
