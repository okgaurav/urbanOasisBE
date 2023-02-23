package com.mongo.backend.model.entity.cart;

import com.mongo.backend.model.entity.account.Address;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @NonNull
    private String deliveryId;
    private List<Tracking> trackingStatus = List.of();
    private String partnerId;
    private String expectedDate;
    private Address address;

    public String getDeliveryId() {
        return deliveryId;
    }

    public Delivery setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
        return this;
    }

    public List<Tracking> getTrackingStatus() {
        return trackingStatus;
    }

    public Delivery setTrackingStatus(List<Tracking> trackingStatus) {
        this.trackingStatus = trackingStatus;
        return this;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public Delivery setPartnerId(String partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public Delivery setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Delivery setAddress(Address address) {
        this.address = address;
        return this;
    }
}
