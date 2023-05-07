package com.shop.services;

import java.util.List;
import java.util.Optional;

import com.shop.struct.Product;

public interface ProductService {

    Optional<Product> getById(Long id);

    Optional<Product> getByProductLink(String productLink);

    List<Product> getAll();

    List<Product> getAllByCategory(String category);

    void saveProduct(Product product);

    void disableProduct(Long id);

    void enableProduct(Long id);
}
