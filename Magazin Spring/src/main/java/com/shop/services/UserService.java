package com.shop.services;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.shop.struct.User;

public interface UserService {

	Optional<User> getUserId(Long id);

	Optional<User> getUserEmail(String email);

	Optional<User> getLoggedUser(Authentication authentication);

	void addUser(User user);

	void updateUser(User user);
	
	boolean checkEmail(String email);

}
