package com.shop.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.repository.ProductRepository;
import com.shop.struct.Product;

@Service
public class CartService {

	@Autowired
	private ProductRepository productRepository;
	
	private List<Product> products = new ArrayList<Product>();
	
	public BigDecimal totalPrice = new BigDecimal("0");

	public List<Product> getAllproducts(){
		return products;
	}
	
	public void addProduct(Product product) {
		String price = String.valueOf(product.getPrice());
		totalPrice = totalPrice.add(new BigDecimal(price));
		products.add(product);
	}
	
	public void delete(String id) {
		// TODO implementare
	}
	
	public void checkout() {
		totalPrice = new BigDecimal("0");
		products.removeAll(products);
	}
}
