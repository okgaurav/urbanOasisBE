package com.mongo.backend.model.utils;

import com.mongo.backend.model.api.fashion.FashionApiDto;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static Update patch(FashionApiDto from) {
        Update update = new Update();
        if (from.getBrand() != null) {
            update.set("brand",from.getBrand());
        }
        if (from.getDescription() != null) {
            update.set("description",from.getDescription());
        }
        if (from.getpId() != null) {
            update.set("pid",from.getpId());
        }
        if (from.getProductName() != null) {
            update.set("productName",from.getProductName());
        }
        if (from.getTags() != null) {
            update.set("tags",from.getTags());
        }
        if (from.getRetailPrice() != null) {
            update.set("retailPrice7",from.getRetailPrice());
        }
        if (from.getDiscountedPrice() != null) {
            update.set("discountedPrice",from.getDiscountedPrice());
        }
        if (from.getDiscount() != null) {
            update.set("discount",from.getDiscount());
        }
        if (from.getRatingCount() != null) {
            update.set("ratingCount",from.getRatingCount());
        }
        if (from.getRating() != null) {
            update.set("rating",from.getRating());
        }
        if (from.getCategory() != null) {
            update.set("category",from.getCategory());
        }
        if (from.getUnits() != null) {
            update.set("units",from.getUnits());
        }
        if (from.getIsVisible() != null) {
            update.set("isVisible",from.getIsVisible());
        }
        return update;
    }

    public static String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
