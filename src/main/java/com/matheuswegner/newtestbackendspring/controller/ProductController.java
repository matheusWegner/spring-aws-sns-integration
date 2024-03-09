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

import com.matheuswegner.newtestbackendspring.domain.product.Product;
import com.matheuswegner.newtestbackendspring.domain.product.ProductDTO;
import com.matheuswegner.newtestbackendspring.service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) {
        Product product = this.productService.create(productDTO);
        return  ResponseEntity.ok().body(product);
    }
    
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = this.productService.getAll();
        return  ResponseEntity.ok().body(products);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathParam(value = "id") String id, @RequestBody ProductDTO ProductDTO) {
        Product product = this.productService.update(id,ProductDTO);
        return  ResponseEntity.ok().body(product);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<com.matheuswegner.newtestbackendspring.domain.product.Product> delete(@PathParam(value = "id") String id) {
        this.productService.delete(id);
        return  ResponseEntity.noContent().build()  ;
    }
}
