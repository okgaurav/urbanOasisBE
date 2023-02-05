package com.example.urbanoasisproducts.model.entity.products;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity(name = "fashion_main")


public class Fashion{
    @Id
    private String unique_id;
    private String product_name;
    private String cat_code;
//    @Type(type = "string-array")
    @Convert(converter = StringArrayType.class)
    @Column(
            name = "subcat",
            columnDefinition = "text[]"
    )
    private List<String> subcat;
    @Convert(converter = StringArrayType.class)
    @Column(
            name = "tags",
            columnDefinition = "text[]"
    )
    private List<String> tags;
    private String pid;
    private Double retail_price;
    private Double discounted_price;
    @Convert(converter = StringArrayType.class)
    @Column(
            name = "images",
            columnDefinition = "text[]"
    )
    private List<String> image;
    private Double discount;
    private String description;
    private Integer rating_count;
    private Double rating;
    private String brand_code;
}
