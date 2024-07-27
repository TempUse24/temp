package com.example.FirstRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FirstRest.entity.Product;
import com.example.FirstRest.service.ProductService;

@RestController
@RequestMapping("/product/api/1.0")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@PostMapping
	@RequestMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createProduct(@RequestBody Product reqProd) {
		
		Product newProd=service.save(reqProd);
		return new ResponseEntity<Product>(newProd,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<Product>> getAll() {
		return new ResponseEntity<List<Product>>(service.fetchAll(),HttpStatus.OK);
	}
	
	@GetMapping("/fetch/{productId}")
	public ResponseEntity<Product> getById(@PathVariable long productId) {
		Product product=service.fetchById(productId);
		if(product!=null)
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> update(@PathVariable long productId, @RequestBody Product product) {
		Product updatedProduct=service.update(productId, product);
		if(product!=null)
			return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{productId}") 
	public ResponseEntity<Void> delete(@PathVariable long productId) {
		if(service.deleteById(productId)) 
			return  ResponseEntity.ok(null);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
