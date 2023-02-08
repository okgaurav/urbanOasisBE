package com.mongo.backend.model.entity;

import com.example.urbanoasisproducts.config.ValidateProductCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id
    String productId;
    @ValidateProductCategory
    String productCategory;
}
