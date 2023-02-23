package com.mongo.backend.model.api.cart;

import com.mongo.backend.model.entity.cart.TrackingStatus;
import lombok.NonNull;

public class TrackingApiDto {
    @NonNull
    private String trackingId;
    private TrackingStatus trackingStatus;
    private String dateTime;
    private String trackingComment;

    public String getTrackingId() {
        return trackingId;
    }

    public TrackingApiDto setTrackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    public TrackingStatus getTrackingStatus() {
        return trackingStatus;
    }

    public TrackingApiDto setTrackingStatus(TrackingStatus trackingStatus) {
        this.trackingStatus = trackingStatus;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public TrackingApiDto setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getTrackingComment() {
        return trackingComment;
    }

    public TrackingApiDto setTrackingComment(String trackingComment) {
        this.trackingComment = trackingComment;
        return this;
    }

}
