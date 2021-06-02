package com.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.services.UserService;
import com.shop.struct.User;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	User user;
	
	@RequestMapping("/profile")
	public String showProfile(Model model) {
		model.addAttribute("pageTitle", "Profile");
		return "profile";	
	}
	
	@RequestMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("pageTitle", "Login");

		return "login";	
	}
	@RequestMapping("/register")
	public String postUser(Model model) {
		model.addAttribute("userInfo" , new User());
		model.addAttribute("pageTitle", "Register");
		return "register";	
	}
	
	@PostMapping("/register_done")
	public String addUser(@ModelAttribute User userInfo , Model model) {
		System.out.println(userInfo);
		userService.addUser(userInfo);
		return "redirect:users";
	}
	// Temp
	@RequestMapping("/users")
	public String niceList(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("usersId", users);
		return "users";
	}
}
