package com.mongo.backend.model.entity.fashion;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fashion{
    @Transient // don't want that field in MongoDb
    public static final String SEQUENCE_NAME = "fashion_product_sequence";
    @Id
    private String uniqueId;
    private String productName;
    private String category;
    private String pId;
    private List<String> tags;
    private Double retailPrice;
    private Double discountedPrice;
    private Double discount;
    private String description;
    private Double ratingCount;
    private Double rating;
    private String brand;
    private Integer units;
    private Boolean isVisible;
}
