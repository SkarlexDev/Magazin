package com.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.repository.ProductRepository;
import com.shop.struct.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll()
		.forEach(products::add);
		return products;
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}
	
	public Product findByproductlink(String productlink) {
		return productRepository.findByproductlink(productlink);
	}


	public List<Product> findAllByCategory(String category) {
		List<Product> products = new ArrayList<>();
		productRepository.findAllByCategory(category)
		.forEach(products::add);
		return products;
		
	}
	public void addproduct(Product product) {
		productRepository.save(product);
	}
	
	public void editproduct(Product product) {
		productRepository.save(product);
	}
	
	public void deleteproduct(Long id) {
		productRepository.deleteById(id);			  
	}



}
