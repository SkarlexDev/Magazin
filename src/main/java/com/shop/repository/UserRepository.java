package com.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.struct.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public User findByEmail(String email);
	
	public Optional<User> findById(Long id);
	
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findUserByEmail(@Param("email") String email);
	
}
