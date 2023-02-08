package com.mongo.backend.model.entity;


import com.mongo.backend.config.ValidateProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data

@Document
public class Product {
    @Id
    String productId;
    @ValidateProductCategory
    String productCategory;
}
