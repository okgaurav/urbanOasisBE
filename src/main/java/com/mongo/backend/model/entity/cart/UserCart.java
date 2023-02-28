package com.mongo.backend.model.entity.cart;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserCart {
    private String cartId;
    private String accountId;
    private UserCollection collection;
    private Delivery deliveryDetails;
    private Integer cartValue;

    public String getCartId() {
        return cartId;
    }

    public UserCart setCartId(String cartId) {
        this.cartId = cartId;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public UserCart setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public UserCollection getCollection() {
        return collection;
    }

    public UserCart setCollection(UserCollection collection) {
        this.collection = collection;
        return this;
    }

    public Delivery getDeliveryDetails() {
        return deliveryDetails;
    }

    public UserCart setDeliveryDetails(Delivery deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
        return this;
    }

    public Integer getCartValue() {
        return cartValue;
    }

    public UserCart setCartValue(Integer cartValue) {
        this.cartValue = cartValue;
        return this;
    }
}
