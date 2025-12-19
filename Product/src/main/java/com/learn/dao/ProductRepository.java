package com.learn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learn.beans.Product;


/*
 * 
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT * FROM products WHERE unit_price > ?1", nativeQuery = true)
	List<Product> findbyPrice(int price);
}
