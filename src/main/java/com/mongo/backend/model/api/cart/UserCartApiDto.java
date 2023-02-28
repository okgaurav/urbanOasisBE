package com.mongo.backend.model.api.cart;

public class UserCartApiDto {
    private String cartId;
    private String accountId;
    private UserCollectionApiDto collection;
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

    public UserCollectionApiDto getCollection() {
        return collection;
    }

    public UserCartApiDto setCollection(UserCollectionApiDto collection) {
        this.collection = collection;
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
