package com.matheuswegner.newtestbackendspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuswegner.newtestbackendspring.domain.category.exception.CategoryNotFoundException;
import com.matheuswegner.newtestbackendspring.domain.product.Product;
import com.matheuswegner.newtestbackendspring.domain.product.ProductDTO;
import com.matheuswegner.newtestbackendspring.domain.product.exception.ProductNotFoundException;
import com.matheuswegner.newtestbackendspring.repository.ProductRepository;
import com.matheuswegner.newtestbackendspring.service.aws.AwsSnsService;
import com.matheuswegner.newtestbackendspring.service.aws.MessageDTO;
@Service
public class ProductService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AwsSnsService awsSnsService;

    public Product create(ProductDTO productData) {
        categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);
        Product newProduct  = new Product(productData);
        this.productRepository.save(newProduct);
        this.awsSnsService.publish(new MessageDTO(newProduct.toString()));
        return newProduct;
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }
    
    public Product update(String id ,ProductDTO productData) {
        Product updateProduct  = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);
        updateProduct.setCategory(productData.categoryId());
        if(!updateProduct.getTitle().isEmpty()) updateProduct.setTitle(productData.title());
        if(!updateProduct.getDescription().isEmpty()) updateProduct.setTitle(productData.description());
        if(!(updateProduct.getPrice() == null)) updateProduct.setPrice(productData.price());
        this.productRepository.save(updateProduct);

        this.awsSnsService.publish(new MessageDTO(updateProduct.toString()));
        return updateProduct;
    }

    public Product delete(String id) {
        Product deleteProduct  = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(deleteProduct);
        return deleteProduct;
    }
}
