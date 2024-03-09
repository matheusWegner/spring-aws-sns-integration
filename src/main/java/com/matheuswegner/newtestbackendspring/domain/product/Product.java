package com.matheuswegner.newtestbackendspring.domain.product;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "product")
@Data
@NoArgsConstructor

public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String category;

    public Product(ProductDTO productData) {
        this.description  =productData.description();
        this.title  =productData.title();
        this.price  =productData.price();
        this.ownerId  =productData.ownerId();
        this.category = productData.categoryId();
    }

     @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description", this.description);
        jsonObject.put("title", this.title);
        jsonObject.put("ownerId",this.ownerId);
        jsonObject.put("id", this.id);
        jsonObject.put("categoryId", this.category);
        jsonObject.put("price", this.category);
        return jsonObject.toString();
    }
}
