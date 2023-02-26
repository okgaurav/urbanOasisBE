package com.mongo.backend.model.entity.account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document
public class Address {
    @Id
    private String addressId;
    private String accountId;
    private String houseNo;
    private String street;
    private String addressLine1;
    private String addressLine2;
    private String landMark;
    private String pinCode;
    private String state;
    private String country;
    private List<String> phoneNumber = List.of();

    public String getAddressId() {
        return addressId;
    }

    public String getAccountId() {
        return accountId;
    }

    public Address setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public Address setAddressId(String addressId) {
        this.addressId = addressId;
        return this;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public Address setHouseNo(String houseNo) {
        this.houseNo = houseNo;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public Address setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public Address setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public String getLandMark() {
        return landMark;
    }

    public Address setLandMark(String landMark) {
        this.landMark = landMark;
        return this;
    }

    public String getPinCode() {
        return pinCode;
    }

    public Address setPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public Address setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
