package com.mongo.backend.model.entity.cart;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Document
public class Tracking {
    @Id
    @NonNull
    private String trackingId;
    private TrackingStatus trackingStatus;
    private String dateTime;
    private String trackingComment;

    public String getTrackingId() {
        return trackingId;
    }

    public Tracking setTrackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    public TrackingStatus getTrackingStatus() {
        return trackingStatus;
    }

    public Tracking setTrackingStatus(TrackingStatus trackingStatus) {
        this.trackingStatus = trackingStatus;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Tracking setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getTrackingComment() {
        return trackingComment;
    }

    public Tracking setTrackingComment(String trackingComment) {
        this.trackingComment = trackingComment;
        return this;
    }

}
