package com.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.struct.Product;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

		List<Product> findAllByCategory(String category);
		Product findByproductlink(String productlink);

		@Procedure
		int GET_TOTAL_PRODUCTS();
		
		@Procedure 
		int GET_TOTAL_PRODUCTS_DISABLED();
		
		@Query(value = "SELECT GET_TOTAL_CATEGORY()", nativeQuery = true)
		int GET_TOTAL_CATEGORY();
		
}
