package com.shop.services;

import com.shop.struct.CartItem;
import com.shop.struct.Product;
import com.shop.struct.User;

import java.math.BigDecimal;
import java.util.List;

public interface CartItemService {

    List<CartItem> getCartItems(User user);

    void addProduct(Product product, User user);

    void removeProduct(Product product, User user);

    void checkout(User user);

    BigDecimal totalPrice(List<CartItem> listCartItems);
}
