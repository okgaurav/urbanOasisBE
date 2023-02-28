package com.mongo.backend.mapper;

import com.mongo.backend.model.api.cart.DeliveryApiDto;
import com.mongo.backend.model.entity.cart.Delivery;

import static com.mongo.backend.mapper.MapperUtils.checkNull;
import static com.mongo.backend.mapper.MapperUtils.mapIfNotNull;
import static com.mongo.backend.mapper.MapperUtils.mapListIfNotNull;
import static com.mongo.backend.model.utils.Utils.isNotNull;

public class DeliveryMapper {
    public static DeliveryApiDto toApi(Delivery delivery) {
        if(isNotNull(delivery)) {
            DeliveryApiDto deliveryApiDto = new DeliveryApiDto()
                    .setDeliveryId(delivery.getDeliveryId())
                    .setAddress(mapIfNotNull(AddressMapper::toApi,delivery.getAddress()))
                    .setExpectedDate(checkNull(delivery.getExpectedDate()))
                    .setPartnerId(checkNull(delivery.getPartnerId()))
                    .setTrackingStatus(mapListIfNotNull(TrackingMapper::toApi,delivery.getTrackingStatus()));
            return deliveryApiDto;
        }
        return null;
    }

    public static Delivery toEntity(DeliveryApiDto deliveryApiDto) {
        if(isNotNull(deliveryApiDto)) {
            Delivery delivery = new Delivery()
                    .setDeliveryId(deliveryApiDto.getDeliveryId())
                    .setExpectedDate(checkNull(deliveryApiDto.getExpectedDate()))
                    .setPartnerId(checkNull(deliveryApiDto.getPartnerId()))
                    .setAddress(mapIfNotNull(AddressMapper::toEntity,deliveryApiDto.getAddress()))
                    .setTrackingStatus(mapListIfNotNull(TrackingMapper::toEntity,deliveryApiDto.getTrackingStatus()));
            return delivery;
        }
        return null;
    }
}
