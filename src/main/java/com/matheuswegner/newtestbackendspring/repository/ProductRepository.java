package com.matheuswegner.newtestbackendspring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.matheuswegner.newtestbackendspring.domain.product.Product;

public interface ProductRepository extends MongoRepository<Product,String>{
    
}
