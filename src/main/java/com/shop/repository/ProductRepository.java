package com.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.struct.Product;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

		List<Product> findAllByCategory(String category);

		Product findByproductlink(String productlink);

}
