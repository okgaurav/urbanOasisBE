package com.mongo.backend.model.api.cart;

import lombok.NonNull;

import java.util.List;

public class UserCartApiDto {
    @NonNull
    private String cartId;
    private String accountId;
    private List<UserCollectionApiDto> collectionList = List.of();
    private DeliveryApiDto deliveryDetails;
    private Integer cartValue;

    public String getCartId() {
        return cartId;
    }

    public UserCartApiDto setCartId(String cartId) {
        this.cartId = cartId;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public UserCartApiDto setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public List<UserCollectionApiDto> getCollectionList() {
        return collectionList;
    }

    public UserCartApiDto setCollectionList(List<UserCollectionApiDto> collectionList) {
        this.collectionList = collectionList;
        return this;
    }

    public DeliveryApiDto getDeliveryDetails() {
        return deliveryDetails;
    }

    public UserCartApiDto setDeliveryDetails(DeliveryApiDto deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
        return this;
    }

    public Integer getCartValue() {
        return cartValue;
    }

    public UserCartApiDto setCartValue(Integer cartValue) {
        this.cartValue = cartValue;
        return this;
    }
}
