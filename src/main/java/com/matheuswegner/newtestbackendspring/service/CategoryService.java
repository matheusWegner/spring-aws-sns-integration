package com.matheuswegner.newtestbackendspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuswegner.newtestbackendspring.domain.category.Category;
import com.matheuswegner.newtestbackendspring.domain.category.CategoryDTO;
import com.matheuswegner.newtestbackendspring.domain.category.exception.CategoryNotFoundException;
import com.matheuswegner.newtestbackendspring.repository.CategoryRepository;
import com.matheuswegner.newtestbackendspring.service.aws.AwsSnsService;
import com.matheuswegner.newtestbackendspring.service.aws.MessageDTO;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AwsSnsService awsSnsService;
    public Category create(CategoryDTO categoryData) {
        Category newCategory  = new Category(categoryData);
        this.categoryRepository.save(newCategory);
        this.awsSnsService.publish(new MessageDTO(newCategory.toString()));
        return newCategory;
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Optional<Category> getById(String id) {
        return this.categoryRepository.findById(id);
    }
    
    public Category update(String id ,CategoryDTO categoryData) {
        Category updateCategory  = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        if(!updateCategory.getTitle().isEmpty()) updateCategory.setTitle(categoryData.title());
        if(!updateCategory.getDescription().isEmpty()) updateCategory.setTitle(categoryData.description());
        this.categoryRepository.save(updateCategory);
        this.awsSnsService.publish(new MessageDTO(updateCategory.toString()));

        return updateCategory;
    }

    public Category delete(String id) {
        Category deleteCategory  = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.categoryRepository.delete(deleteCategory);
        return deleteCategory;
    }
}
