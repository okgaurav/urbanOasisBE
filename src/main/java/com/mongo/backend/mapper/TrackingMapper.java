package com.mongo.backend.mapper;

import com.mongo.backend.model.api.cart.TrackingApiDto;
import com.mongo.backend.model.entity.cart.Tracking;
import org.springframework.beans.BeanUtils;

public class TrackingMapper {
    public static TrackingApiDto toApi(Tracking tracking) {
        TrackingApiDto trackingApiDto = new TrackingApiDto();
        BeanUtils.copyProperties(tracking, trackingApiDto);
        return trackingApiDto;
    }

    public static Tracking toEntity(TrackingApiDto trackingApiDto) {
        Tracking tracking = new Tracking();
        BeanUtils.copyProperties(trackingApiDto, tracking);
        return tracking;
    }
}
