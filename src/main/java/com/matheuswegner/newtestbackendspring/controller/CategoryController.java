package com.matheuswegner.newtestbackendspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheuswegner.newtestbackendspring.domain.category.Category;
import com.matheuswegner.newtestbackendspring.domain.category.CategoryDTO;
import com.matheuswegner.newtestbackendspring.service.CategoryService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO categoryDTO) {
        Category category = this.categoryService.create(categoryDTO);
        return  ResponseEntity.ok().body(category);
    }
    
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> category = this.categoryService.getAll();
        return  ResponseEntity.ok().body(category);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathParam(value = "id") String id, @RequestBody CategoryDTO categoryDTO) {
        Category category = this.categoryService.update(id , categoryDTO);
        return  ResponseEntity.ok().body(category);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Category> delete(@PathParam(value = "id") String id) {
        this.categoryService.delete(id);
        return  ResponseEntity.noContent().build()  ;
    }
}
