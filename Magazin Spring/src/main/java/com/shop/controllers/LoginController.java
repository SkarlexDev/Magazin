package com.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.services.UserService;
import com.shop.struct.User;

@Controller
public class LoginController {

	private final UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("pageTitle", "Login");
		return "login/login";
	}

	@RequestMapping("/register")
	public String postUser(Model model) {
		model.addAttribute("userInfo", new User());
		model.addAttribute("pageTitle", "Register");
		return "login/register";
	}

	@PostMapping("/register_done")
	public String addUser(@ModelAttribute User userInfo) {
		userService.addUser(userInfo);
		return "redirect:/home";
	}
		
}
