package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.services.ProductService;
import com.shop.services.UserService;
import com.shop.struct.Product;
import com.shop.struct.User;

import java.util.List;
import java.util.Optional;

@RestController
public class API {

	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	@RequestMapping("/api/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping("/api/users/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		return userService.getUserId(id);
	}

	@RequestMapping("/api/products")
	public List<Product> getallProducts() {
		return productService.findAll();
	}

	@RequestMapping("/api/products/{id}")
	public Optional<Product> getProduct(@PathVariable Long id) {
		return productService.findById(id);
	}

	@RequestMapping("/api/shop/{category}/{productlink}")
	public Product findById(@PathVariable String category, @PathVariable String productlink) {

		return productService.findByproductlink(productlink);
	}

}
