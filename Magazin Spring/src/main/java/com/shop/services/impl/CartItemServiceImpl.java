package com.shop.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.repository.CartItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.OrderitemsRepository;
import com.shop.services.CartItemService;
import com.shop.struct.CartItem;
import com.shop.struct.Order;
import com.shop.struct.Orderitems;
import com.shop.struct.Product;
import com.shop.struct.User;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    private final OrderRepository orderRepository;

    private final OrderitemsRepository orderitemsRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, OrderRepository orderRepository,
            OrderitemsRepository orderitemsRepository) {
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderitemsRepository = orderitemsRepository;
    }

    @Override
    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    @Override
    public void addProduct(Product product, User user) {
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setUser(user);
        }
        List<CartItem> listCartItems = cartItemRepository.findByUser(user);

        boolean singleProduct = true;

        for (CartItem lci : listCartItems) {
            if (lci.getProduct().getId().equals(cartItem.getProduct().getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                singleProduct = false;
            }
        }
        if (singleProduct) {
            cartItem.setQuantity(1);
        }
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeProduct(Product product, User user) {
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
        List<CartItem> listCartItems = cartItemRepository.findByUser(user);
        for (CartItem lci : listCartItems) {
            if (lci.getProduct().getId().equals(cartItem.getProduct().getId())) {
                if (cartItem.getQuantity() == 1) {
                    cartItemRepository.deleteByUserAndProduct(user.getId(), product.getId());
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                }
            }
        }
        cartItemRepository.save(cartItem);
    }

    @Override
    public void checkout(User user) {
        List<CartItem> listCartItems = cartItemRepository.findByUser(user);
        if (listCartItems.size() != 0) {
            Order order = new Order(user);
            orderRepository.save(order);
            double totalPrice = 0;
            for (CartItem lci : listCartItems) {
                double price = lci.getProduct().getPrice() * lci.getQuantity();
                Orderitems oi = new Orderitems(lci.getId(), lci.getQuantity(), price, order.getId(), order,
                        lci.getProduct());
                totalPrice = totalPrice + price;
                orderitemsRepository.save(oi);
            }
            order.setTotalPrice(totalPrice);
            order.saveOrder(user.getFullName(), user.getZip(), user.getAddress(), user.getState(),
                    user.getPhone(), user.getCity(), user.getEmail());
            orderRepository.save(order);
            cartItemRepository.deleteByUser(user.getId());
        }
    }

    @Override
    public BigDecimal totalPrice(List<CartItem> listCartItems) {
        BigDecimal totalPrice = new BigDecimal("0");
        if (listCartItems != null) {
            for (CartItem lci : listCartItems) {
                String price = String.valueOf(lci.getProduct().getPrice() * lci.getQuantity());
                totalPrice = totalPrice.add(new BigDecimal(price));
            }
        }
        return totalPrice;
    }

}
