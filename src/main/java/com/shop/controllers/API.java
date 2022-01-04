package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public Optional<User> getUser(@PathVariable int id) {
		return userService.getUserId(id);
	}

	@RequestMapping("/api/products")
	public List<Product> getallProducts() {
		return productService.getAll();
	}

	@RequestMapping("/api/products/{id}")
	public Optional<Product> getProduct(@PathVariable int id) {
		return productService.getById(id);
	}

	@RequestMapping("/api/shop/{category}/{productlink}")
	public Product getById(@PathVariable String category, @PathVariable String productlink) {

		return productService.getByProductlink(productlink);
	}

	/*
	 * 
	 * // for test only !!!! //
	 * 
	 */
	@RequestMapping("/infodata/{id}/{newPW}")
	public String getUserpw(@PathVariable String id, @PathVariable String newPW) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoded = encoder.encode(newPW);
		User user = userService.getUserEmail(id);
		/* 
		 * temp disable
		 * user.setPassword(encoded);
		 * userService.updateUser(user);
		 */
		userService.CHANGE_PASSWORD(user.getId(), encoded);
		return "NewPW: " + newPW + "<br>" +" Encoded: " + encoded;
	}

}
