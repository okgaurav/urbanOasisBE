package com.mongo.backend.model.entity.cart;

import com.mongo.backend.model.entity.fashion.Fashion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserCollection {
    private String collectionId;
    private String accountId;
    private Integer collectionTotal;
    private String coupanCode;
    private Integer discount;
    private List<Fashion> products = List.of();

    public String getCollectionId() {
        return collectionId;
    }

    public UserCollection setCollectionId(String collectionId) {
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

    public String getAccountId() {
        return accountId;
    }

    public UserCollection setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }
}
