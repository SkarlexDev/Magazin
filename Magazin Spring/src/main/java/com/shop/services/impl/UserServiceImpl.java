package com.shop.services.impl;

import com.shop.configuration.MyUserDetails;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import com.shop.services.UserService;
import com.shop.struct.Role;
import com.shop.struct.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmailOrUsername(login, login);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user.get());
    }

    @Override
    public Optional<User> getUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserEmail(String login) {
        return userRepository.findByEmailOrUsername(login, login);
    }

    @Override
    public Optional<User> getLoggedUser(Authentication authentication) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = Optional.empty();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            user = userRepository.findByEmailOrUsername(email, email);
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(user.getPassword());
        user.setPassword(encoded);
        Role roleUser = roleRepository.findByName("USER");
        user.addRole(roleUser);
        userRepository.save(user);

    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

	@Override
	public boolean checkEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

}
