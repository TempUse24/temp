package com.example.FirstRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FirstRest.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
