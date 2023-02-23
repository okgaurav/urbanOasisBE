package com.mongo.backend.model.api.account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AddressApiDto {
    private String houseNo;
    private String street;
    private String addressLine1;
    private String addressLine2;
    private String landMark;
    private String pinCode;
    private String state;
    private String country;
    private List<String> phoneNumber = List.of();

    public String getHouseNo() {
        return houseNo;
    }

    public AddressApiDto setHouseNo(String houseNo) {
        this.houseNo = houseNo;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressApiDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public AddressApiDto setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public AddressApiDto setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public String getLandMark() {
        return landMark;
    }

    public AddressApiDto setLandMark(String landMark) {
        this.landMark = landMark;
        return this;
    }

    public String getPinCode() {
        return pinCode;
    }

    public AddressApiDto setPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public String getState() {
        return state;
    }

    public AddressApiDto setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressApiDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public AddressApiDto setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
