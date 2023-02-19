package com.mongo.backend.model.entity.fashion;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Integer>rating = List.of();
    private String brand;
    private Integer units;
    private Boolean isVisible;

    public String getUniqueId() {
        return uniqueId;
    }

    public Fashion setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getProductName() {
        return productName;

    }

    public Fashion setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Fashion setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getpId() {
        return pId;
    }

    public Fashion setpId(String pId) {
        this.pId = pId;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public Fashion setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public Fashion setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
        return this;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public Fashion setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
        return this;
    }

    public Double getDiscount() {
        return discount;
    }

    public Fashion setDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Fashion setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Integer> getRating() {
        return rating;
    }

    public void setRating(List<Integer> rating) {
        this.rating = rating;
    }

    public String getBrand() {
        return brand;
    }

    public Fashion setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Integer getUnits() {
        return units;
    }

    public Fashion setUnits(Integer units) {
        this.units = units;
        return this;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public Fashion setIsVisible(Boolean visible) {
        isVisible = visible;
        return this;
    }
}
