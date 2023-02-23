package com.mongo.backend.mapper;

import com.mongo.backend.model.api.cart.DeliveryApiDto;
import com.mongo.backend.model.entity.cart.Delivery;
import org.springframework.beans.BeanUtils;

public class DeliveryMapper {
    public static DeliveryApiDto toApi(Delivery delivery) {
        DeliveryApiDto deliveryApiDto = new DeliveryApiDto();
        BeanUtils.copyProperties(delivery, deliveryApiDto);
        return deliveryApiDto;
    }

    public static Delivery toEntity(DeliveryApiDto deliveryApiDto) {
        Delivery delivery = new Delivery();
        BeanUtils.copyProperties(deliveryApiDto, delivery);
        return delivery;
    }
}
