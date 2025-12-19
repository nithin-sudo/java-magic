package com.learn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.beans.Product;
import com.learn.service.ProductService;

/*
 * 
 */


// This annotation is from Spring framework, specifically for REST API request and 
// automatically converst responses to JSON/XML 
@RestController
@RequestMapping("/products")
public class ProductController {

	
	@Autowired
	private ProductService productService;
	// List<product> is a generic, the List will contain only product objects
	@GetMapping
	public List<Product> getProduct(){
		return productService.findAll();
	}
	
	
	@GetMapping("/byproductid/{id}")
	public ResponseEntity<Product> getProductbyId(@PathVariable Long id){
		
		Optional<Product> ID =  productService.findByID(id);
		if(ID.isPresent()) {
			Product product = ID.get();
			return ResponseEntity.status(HttpStatus.OK).body(product);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		productService.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@PostMapping("/createbulkproducts")
	public ResponseEntity<List<Product>> createProductsbyBulk(@RequestBody List<Product> products){
		List<Product> savedProducts = productService.SaveMultipleProducts(products);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProducts);
	}
	
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		
		Optional<Product> byID = 	productService.findByID(product.getId());
		if(byID.isPresent()) {
			productService.save(product);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteProduct(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@GetMapping("/byprice/{price}")
	public ResponseEntity<List<Product>> findByPrice(@PathVariable Integer price){
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPrice(price));
	}
}
