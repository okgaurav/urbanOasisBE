package com.mongo.backend.model.entity.account;

import com.mongo.backend.model.entity.Comments;
import com.mongo.backend.model.entity.cart.UserCart;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    private String uniqueId;
    private String userType;
    private String profileImage;
    private String Address;
    private BigInteger phoneNo;
    private List<Address> addresses = List.of();
    private boolean isActive;
    private List<Comments> userComments = List.of();
    private List<UserCart> cart = List.of();

    public List<Address> getAddresses() {
        return addresses;
    }

    public UserAccount setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public UserAccount setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public UserAccount setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public UserAccount setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public String getAddress() {
        return Address;
    }

    public UserAccount setAddress(String address) {
        Address = address;
        return this;
    }

    public BigInteger getPhoneNo() {
        return phoneNo;
    }

    public UserAccount setPhoneNo(BigInteger phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserAccount setActive(boolean active) {
        isActive = active;
        return this;
    }

    public List<Comments> getUserComments() {
        return userComments;
    }

    public UserAccount setUserComments(List<Comments> userComments) {
        this.userComments = userComments;
        return this;
    }

    public List<UserCart> getCart() {
        return cart;
    }

    public UserAccount setCart(List<UserCart> cart) {
        this.cart = cart;
        return this;
    }
}
