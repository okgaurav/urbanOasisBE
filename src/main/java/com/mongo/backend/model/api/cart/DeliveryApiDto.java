package com.mongo.backend.model.api.cart;


import com.mongo.backend.model.api.account.AddressApiDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class DeliveryApiDto {
    @NonNull
    private String deliveryId;
    private List<TrackingApiDto> trackingStatus = List.of();
    private String partnerId;
    private String expectedDate;
    private AddressApiDto address;

    public String getDeliveryId() {
        return deliveryId;
    }

    public DeliveryApiDto setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
        return this;
    }

    public List<TrackingApiDto> getTrackingStatus() {
        return trackingStatus;
    }

    public DeliveryApiDto setTrackingStatus(List<TrackingApiDto> trackingStatus) {
        this.trackingStatus = trackingStatus;
        return this;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public DeliveryApiDto setPartnerId(String partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public DeliveryApiDto setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
        return this;
    }

    public AddressApiDto getAddress() {
        return address;
    }

    public DeliveryApiDto setAddress(AddressApiDto address) {
        this.address = address;
        return this;
    }
}
