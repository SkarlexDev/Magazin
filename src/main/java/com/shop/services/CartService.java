package com.shop.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.repository.ProductRepository;
import com.shop.struct.Product;

@Service
public class CartService {

	@Autowired
	private ProductRepository productRepository;
	
	private List<Product> products = new ArrayList<Product>();

	public Map<Integer, Product> productCart = new HashMap<Integer, Product>();
	int sizemap = 0;
	
	public BigDecimal totalPrice = new BigDecimal("0");

	public List<Product> getAllproducts(){
		return products;
	}
	
	public void addProduct(Product product) {
		String price = String.valueOf(product.getPrice());
		totalPrice = totalPrice.add(new BigDecimal(price));
		products.add(product);
		if(sizemap == 0) {
			sizemap = productCart.size() + 1;
		}else{
			sizemap +=1;
		}		
		productCart.put(sizemap,product);
		
		for (Map.Entry<Integer, Product> entry : productCart.entrySet()) {
		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		   // System.out.println(entry.getValue().getPrice());
		}		
		
	}
	
	public void delete(int id) {
		Product removethis = productCart.get(id);
		List<Object> toRemove = new ArrayList<Object>();				
		toRemove.add(removethis);
		String price = String.valueOf(removethis.getPrice());
		totalPrice = totalPrice.subtract(new BigDecimal(price));
		products.removeAll(toRemove);
		productCart.remove(id);
		System.out.println(productCart);
		System.out.println(products);

	}
	
	public void checkout() {
		sizemap =0;
		totalPrice = new BigDecimal("0");
		products.removeAll(products);
		productCart.clear();
	}
}
