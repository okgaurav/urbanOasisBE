package com.mongo.backend.model.entity.cart;

import com.mongo.backend.model.entity.fashion.Fashion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class UserCollection {
    @Id
    @NotNull
    private String collectionId;
    private Integer collectionTotal;
    private String coupanCode;
    private Integer discount;
    private List<Fashion> products = List.of();

    @NotNull
    public String getCollectionId() {
        return collectionId;
    }

    public UserCollection setCollectionId(@NotNull String collectionId) {
        this.collectionId = collectionId;
        return this;
    }

    public Integer getCollectionTotal() {
        return collectionTotal;
    }

    public UserCollection setCollectionTotal(Integer collectionTotal) {
        this.collectionTotal = collectionTotal;
        return this;
    }

    public String getCoupanCode() {
        return coupanCode;
    }

    public UserCollection setCoupanCode(String coupanCode) {
        this.coupanCode = coupanCode;
        return this;
    }

    public Integer getDiscount() {
        return discount;
    }

    public UserCollection setDiscount(Integer discount) {
        this.discount = discount;
        return this;
    }

    public List<Fashion> getProducts() {
        return products;
    }

    public UserCollection setProducts(List<Fashion> products) {
        this.products = products;
        return this;
    }
}
