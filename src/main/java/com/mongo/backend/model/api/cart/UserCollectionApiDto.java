package com.mongo.backend.model.api.cart;

import com.mongo.backend.model.api.fashion.FashionApiDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserCollectionApiDto {
    @NotNull
    private String collectionId;
    private Integer collectionTotal;
    private String coupanCode;
    private Integer discount;
    private List<FashionApiDto> products = List.of();

    @NotNull
    public String getCollectionId() {
        return collectionId;
    }

    public UserCollectionApiDto setCollectionId(@NotNull String collectionId) {
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
}
