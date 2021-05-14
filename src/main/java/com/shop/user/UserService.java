package com.shop.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class UserService {


	private ArrayList<User> users = new ArrayList<User>();
	
	public ArrayList<User> getAllUsers() {
		return users;
	}
	
	public void setUser(User user) {
		user.setId(users.size()+1);
		users.add(user);
	}	
	
	public ArrayList<User> getUserList(){
		return null;		
	}
	
	public User getEmail(String id) {
		User user = users.stream()
				.filter(t -> id.equals(t.getEmail()))
				.findFirst()
				.orElse(null);

		return user;
	}
}
