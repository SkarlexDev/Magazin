package com.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.struct.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	public User findByEmail(String email);
	
	public Optional<User> findById(Long id);
		
	@Procedure
	String CHANGE_PASSWORD(int user_u_id, String newPw);
	
	@Query(value = "SELECT GET_TOTAL_ORDERS_BY_USER(?1)", nativeQuery = true)
	int GET_TOTAL_ORDERS_BY_USER(int user_u_id);
	
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findUserByEmail(@Param("email") String email);
 
	
}
