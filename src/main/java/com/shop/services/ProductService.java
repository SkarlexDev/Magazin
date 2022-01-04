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

	public List<Product> getAll() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	public Optional<Product> getById(int id) {
		return productRepository.findById(id);
	}

	public Product getByProductlink(String productlink) {
		return productRepository.findByproductlink(productlink);
	}

	public List<Product> getAllByCategory(String category) {
		List<Product> products = new ArrayList<>();
		productRepository.findAllByCategory(category).forEach(products::add);
		return products;

	}

	public void addproduct(Product product) {
		product.setActive(true);
		productRepository.save(product);
	}

	public void editproduct(Product product) {
		product.setActive(true);
		productRepository.save(product);
	}

	public void disableProduct(int id) {
		Product product = productRepository.findById(id).get();
		product.setActive(false);
		productRepository.save(product);
	}

	public void enableProduct(int id) {
		Product product = productRepository.findById(id).get();
		product.setActive(true);
		productRepository.save(product);
	}
	
	public int GET_TOTAL_PRODUCTS()
	{
		return productRepository.GET_TOTAL_PRODUCTS();
	}
	
	public int GET_TOTAL_PRODUCTS_DISABLED()
	{
		return productRepository.GET_TOTAL_PRODUCTS_DISABLED();
	}
	
	public int GET_TOTAL_CATEGORY()
	{
		return productRepository.GET_TOTAL_CATEGORY();
	}

}
