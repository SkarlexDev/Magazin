package com.shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.repository.CartItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.OrderitemsRepository;
import com.shop.struct.CartItem;
import com.shop.struct.Order;
import com.shop.struct.Orderitems;
import com.shop.struct.Product;
import com.shop.struct.User;

@Service
public class CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderitemsRepository orderitemsRepository;

	public List<CartItem> getCartItems(User user) {
		return cartItemRepository.findByUser(user);
	}

	public void addProduct(Product product, User user) {
		CartItem cartItem = cartItem(user, product);

		if (cartItem == null) {
			cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setUser(user);
		}

		boolean singleitem = true;

		for (CartItem lci : listCartItems(user)) {
			if (lci.getProduct().getId()==(cartItem.getProduct().getId())) {
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				singleitem = false;
			}
		}

		if (singleitem) {
			cartItem.setQuantity(1);
		}
		cartItemRepository.save(cartItem);
	}

	public void removeProduct(User user, Product product) {
		CartItem cartItem = cartItem(user, product);
		
		for (CartItem lci : listCartItems(user)) {
			if (lci.getProduct().getId()==(cartItem.getProduct().getId())) {
				if (cartItem.getQuantity() == 1) {
					cartItemRepository.deleteByUserAndProduct(user.getId(), product.getId());
				} else {
					cartItem.setQuantity(cartItem.getQuantity() - 1);
				}
			}
		}
		cartItemRepository.save(cartItem);
	}

	public void checkout(User user) {
		
		if (listCartItems(user).size() != 0) {
			Order order = new Order(user);
			orderRepository.save(order);
			double totalprice = 0;
			for (CartItem lci : listCartItems(user)) {
				double price = lci.getProduct().getPrice() * lci.getQuantity();
				Orderitems oi = new Orderitems(lci.getId(), lci.getQuantity(), price, order.getId(), order,
						lci.getProduct());
				totalprice = totalprice + price;
				orderitemsRepository.save(oi);
			}

			order.setTotalPrice(totalprice);
			order.saveOrder(user.getLastName().toString(), user.getFirstName().toString(), user.getCodPostal(),
					user.getAdresa(), user.getJudet(), user.getTelefon(), user.getOras(), user.getEmail());
			orderRepository.save(order);
			cartItemRepository.deleteByUser(user.getId());
		}
	}
	
	//////////////////////////////////////
	
	public CartItem cartItem(User user, Product product)
	{
		return cartItemRepository.findByUserAndProduct(user, product);
	}
	
	public List<CartItem> listCartItems(User user)
	{
		return cartItemRepository.findByUser(user);
	}

}
