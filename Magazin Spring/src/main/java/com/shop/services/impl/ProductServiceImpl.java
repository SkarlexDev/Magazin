package com.shop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shop.repository.ProductRepository;
import com.shop.services.ProductService;
import com.shop.struct.Product;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> getByProductLink(String productLink) {
        return productRepository.findByproductlink(productLink);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public List<Product> getAllByCategory(String category) {
        return new ArrayList<>(productRepository.findAllByCategory(category));
    }

    @Override
    public void saveProduct(Product product) {
        product.setActive(true);
        productRepository.save(product);
    }

    @Override
    public void disableProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setActive(false);
            productRepository.save(product.get());
        }
    }

    @Override
    public void enableProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setActive(true);
            productRepository.save(product.get());
        }
    }
}
