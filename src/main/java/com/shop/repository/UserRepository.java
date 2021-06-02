package com.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.struct.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByEmail(String email);
	
}
