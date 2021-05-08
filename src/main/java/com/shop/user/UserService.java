package com.shop.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {


	User user1 = new User(
			"u1", 
			"Jany", 
			"Lawrence");

	User user2 = new User(
			"u2", 
			"Jadon", 
			"Mills");
	private List<User> users = Arrays.asList(user1, user2);	
	
	public List<User> getAllUsers() {
		return users;
	}
}
