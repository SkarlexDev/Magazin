package com.shop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.struct.CartItem;
import com.shop.struct.Product;
import com.shop.struct.User;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	public List<CartItem> findByUser(User user);
	public CartItem findByUserAndProduct(User user, Product product);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CartItem c WHERE c.user.id = :userID AND c.product.id = :productID")
	public void deleteByUserAndProduct(@Param("userID") Long userID,@Param("productID") Long productID);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CartItem c WHERE c.user.id = :userID")
	public void deleteByUser(Long userID);
}
