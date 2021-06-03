package com.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll()
		.forEach(users::add);
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
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
 
}
