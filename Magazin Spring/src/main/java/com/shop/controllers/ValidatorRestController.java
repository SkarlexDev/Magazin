package com.shop.controllers;

import com.shop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidatorRestController {

	private final UserService userService;
	
	public ValidatorRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	@PostMapping("/check_email/{email}")
	public ResponseEntity<String> postStudent(@PathVariable String email) {
		if(userService.checkEmail(email)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	    return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
