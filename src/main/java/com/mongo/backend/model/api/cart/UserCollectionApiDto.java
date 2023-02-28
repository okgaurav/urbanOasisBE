package com.mongo.backend.model.api.cart;

import com.mongo.backend.model.api.fashion.FashionApiDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserCollectionApiDto {
    private String collectionId;
    private String accountId;
    private Integer collectionTotal;
    private String coupanCode;
    private Integer discount;
    private List<FashionApiDto> products = List.of();
    public String getCollectionId() {
        return collectionId;
    }

    public UserCollectionApiDto setCollectionId(String collectionId) {
        this.collectionId = collectionId;
        return this;
    }

    public Integer getCollectionTotal() {
        return collectionTotal;
    }

    public UserCollectionApiDto setCollectionTotal(Integer collectionTotal) {
        this.collectionTotal = collectionTotal;
        return this;
    }

    public String getCoupanCode() {
        return coupanCode;
    }

    public UserCollectionApiDto setCoupanCode(String coupanCode) {
        this.coupanCode = coupanCode;
        return this;
    }

    public Integer getDiscount() {
        return discount;
    }

    public UserCollectionApiDto setDiscount(Integer discount) {
        this.discount = discount;
        return this;
    }

    public List<FashionApiDto> getProducts() {
        return products;
    }

    public UserCollectionApiDto setProducts(List<FashionApiDto> products) {
        this.products = products;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public UserCollectionApiDto setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }
}
