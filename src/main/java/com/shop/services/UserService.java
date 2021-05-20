package com.shop.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.shop.struct.User;



@Service
public class UserService {


	private List<User> users = new ArrayList<User>();
	
	public List<User> getAllUsers() {
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
