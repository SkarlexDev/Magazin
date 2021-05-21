package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.services.UserService;
import com.shop.struct.User;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	User user;

	@RequestMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("pageTitle", "Login");

		return "login";	
	}
}
