package com.example.FirstRest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FirstRest.entity.Product;
import com.example.FirstRest.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;
	public Product save(Product product) {
		return repository.save(product);
	}
	
	public List<Product> fetchAll() {
		return repository.findAll();
	}
	
	public Product fetchById(Long productId) {
		Optional<Product> p= repository.findById(productId);
		if(p.isPresent()) 
			return p.get();
		return null;
	}
	
	public Product update(Long productId, Product product) {
		if(repository.findById(productId).isPresent())
			return repository.save(product);
		return null;
	}
	
	public boolean deleteById(Long productId) {
		if(repository.findById(productId).isPresent()) {
			repository.deleteById(productId);
			return true;
		}
		return false;
	}
}
