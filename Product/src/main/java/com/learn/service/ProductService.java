package com.learn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.beans.Product;
import com.learn.dao.ProductRepository;

/*
 * 
 */

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRespository;

	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRespository.findAll();
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		productRespository.save(product);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		productRespository.deleteById(id);;
	}

	public Optional<Product> findByID(Long id) {
		// TODO Auto-generated method stub
		return productRespository.findById(id);
	}

	public List<Product> findByPrice(Integer price) {
		// TODO Auto-generated method stub
		return productRespository.findbyPrice(price);
	}
	
	
	public List<Product> SaveMultipleProducts(List<Product> products){
		
		List<Product> savedProducts = new ArrayList<>();
		
		for (Product product : products) {
			productRespository.save(product);
			savedProducts.add(product);
		}
		
		return savedProducts;
	}
	
	
}
