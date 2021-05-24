package com.shop.repository;

import org.springframework.data.repository.CrudRepository;

import com.shop.struct.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByEmail(String email);
	
}
