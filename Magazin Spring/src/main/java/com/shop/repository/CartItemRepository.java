package com.shop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.struct.CartItem;
import com.shop.struct.Product;
import com.shop.struct.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUser(User user);

    CartItem findByUserAndProduct(User user, Product product);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.user.id = :userID AND c.product.id = :productID")
    void deleteByUserAndProduct(@Param("userID") Long userID, @Param("productID") Long productID);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.user.id = :userID")
    void deleteByUser(Long userID);
}
