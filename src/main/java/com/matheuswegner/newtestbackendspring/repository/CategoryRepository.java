package com.matheuswegner.newtestbackendspring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.matheuswegner.newtestbackendspring.domain.category.Category;

public interface CategoryRepository extends MongoRepository<Category,String> {
    
}
