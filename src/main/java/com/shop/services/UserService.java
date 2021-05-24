package com.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.repository.UserRepository;
import com.shop.struct.User;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll()
		.forEach(users::add);
		return users;
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	public Optional<User> getUserId(long id) {
		return userRepository.findById(id);
		
	}
	
	public User getUserEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
