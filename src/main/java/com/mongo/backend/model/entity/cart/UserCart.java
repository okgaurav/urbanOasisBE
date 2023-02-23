package com.mongo.backend.model.entity.cart;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserCart {
    @Id
    @NonNull
    private String cartId;
    private String accountId;
    private List<UserCollection> collectionList = List.of();
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

    public List<UserCollection> getCollectionList() {
        return collectionList;
    }

    public UserCart setCollectionList(List<UserCollection> collectionList) {
        this.collectionList = collectionList;
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
