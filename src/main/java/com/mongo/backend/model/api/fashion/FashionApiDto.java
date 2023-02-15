package com.mongo.backend.model.api.fashion;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class FashionApiDto {
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
    public String getUniqueId() {
        return uniqueId;
    }
    public FashionApiDto setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getProductName() {
        return productName;

    }

    public FashionApiDto setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public FashionApiDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getpId() {
        return pId;
    }

    public FashionApiDto setpId(String pId) {
        this.pId = pId;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public FashionApiDto setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public FashionApiDto setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
        return this;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public FashionApiDto setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
        return this;
    }

    public Double getDiscount() {
        return discount;
    }

    public FashionApiDto setDiscount(Double discount) {
        this.discount = discount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FashionApiDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getRatingCount() {
        return ratingCount;
    }

    public FashionApiDto setRatingCount(Double ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public FashionApiDto setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public FashionApiDto setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Integer getUnits() {
        return units;
    }

    public FashionApiDto setUnits(Integer units) {
        this.units = units;
        return this;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public FashionApiDto setIsVisible(Boolean visible) {
        isVisible = visible;
        return this;
    }
}
