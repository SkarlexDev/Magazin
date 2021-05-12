package com.shop.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class userService {


	user user1 = new user(
			"u1", 
			"Jany", 
			"Lawrence");

	user user2 = new user(
			"u2", 
			"Jadon", 
			"Mills");
	private List<user> users = Arrays.asList(user1, user2);	
	
	public List<user> getAllUsers() {
		return users;
	}
	
	public user getEmail(String id) {
		user user = users.stream()
				.filter(t -> id.equals(t.getAdresa()))
				.findFirst()
				.orElse(null);

		return user;
	}
}
