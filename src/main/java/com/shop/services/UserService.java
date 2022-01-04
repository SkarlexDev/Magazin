package com.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.configuration.MyUserDetails;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import com.shop.struct.Role;
import com.shop.struct.User;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public void addUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoded = encoder.encode(user.getPassword());
		user.setPassword(encoded);
		Role roleUser = roleRepository.findByName("USER");
		user.addRole(roleUser);
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserDetails(user);
	}
	
	public User getLoggedUser(Authentication authentication) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = null;
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			user = userRepository.findUserByEmail(email);
			//System.out.println(userRepository.findUserByEmails(email));
		}
		return user;
	}
	
	public int GET_TOTAL_ORDERS_BY_USER(int user_u_id) {		
		return userRepository.GET_TOTAL_ORDERS_BY_USER(user_u_id);
	}
	
	public String CHANGE_PASSWORD(int user_u_id, String newPw)
	{
		return userRepository.CHANGE_PASSWORD(user_u_id, newPw);
		
	}

}
