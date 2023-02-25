package com.mongo.backend.model.api.account;

import com.mongo.backend.model.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class UserAccountApiDto {
    private String uniqueId;
    private String userType;
    private String profileImage;
    private String Address;
    private BigInteger phoneNo;
    private boolean isActive;
    private List<AddressApiDto> addresses = List.of();
    private List<Comments> userComments;

    public String getUniqueId() {
        return uniqueId;
    }

    public List<AddressApiDto> getAddresses() {
        return addresses;
    }

    public UserAccountApiDto setAddresses(List<AddressApiDto> addresses) {
        this.addresses = addresses;
        return this;
    }

    public UserAccountApiDto setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public UserAccountApiDto setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public UserAccountApiDto setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public String getAddress() {
        return Address;
    }

    public UserAccountApiDto setAddress(String address) {
        Address = address;
        return this;
    }

    public BigInteger getPhoneNo() {
        return phoneNo;
    }

    public UserAccountApiDto setPhoneNo(BigInteger phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserAccountApiDto setActive(boolean active) {
        isActive = active;
        return this;
    }

    public List<Comments> getUserComments() {
        return userComments;
    }

    public UserAccountApiDto setUserComments(List<Comments> userComments) {
        this.userComments = userComments;
        return this;
    }
}
