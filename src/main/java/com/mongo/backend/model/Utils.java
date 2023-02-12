package com.mongo.backend.model;

import com.mongo.backend.model.api.fashion.FashionApiDto;

public class Utils {
    public static FashionApiDto patch(FashionApiDto to,FashionApiDto from) {
        if (from.getBrand() != null) {
            to.setBrand(from.getBrand());
        }
        if (from.getDescription() != null) {
            to.setDescription(from.getDescription());
        }
        if (from.getPId() != null) {
            to.setPId(from.getPId());
        }
        if (from.getProductName() != null) {
            to.setProductName(from.getProductName());
        }
        if (from.getTags() != null) {
            to.setTags(from.getTags());
        }
        if (from.getRetailPrice() != null) {
            to.setRetailPrice(from.getRetailPrice());
        }
        if (from.getDiscountedPrice() != null) {
            to.setDiscountedPrice(from.getDiscountedPrice());
        }
        if (from.getDiscount() != null) {
            to.setDiscount(from.getDiscount());
        }
        if (from.getRatingCount() != null) {
            to.setRatingCount(from.getRatingCount());
        }
        if (from.getRating() != null) {
            to.setRating(from.getRating());
        }
//    private FashionCategory catCode;
        return to;
    }
}
