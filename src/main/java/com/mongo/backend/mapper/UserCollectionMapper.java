package com.mongo.backend.mapper;

import com.mongo.backend.model.api.cart.DeliveryApiDto;
import com.mongo.backend.model.api.cart.UserCollectionApiDto;
import com.mongo.backend.model.entity.cart.Delivery;
import com.mongo.backend.model.entity.cart.UserCollection;
import org.springframework.beans.BeanUtils;

public class UserCollectionMapper {
    public static UserCollectionApiDto toApi(UserCollection userCollection) {
        UserCollectionApiDto userCollectionApiDto = new UserCollectionApiDto();
        BeanUtils.copyProperties(userCollection, userCollectionApiDto);
        return userCollectionApiDto;
    }

    public static Delivery toEntity(DeliveryApiDto deliveryApiDto) {
        Delivery delivery = new Delivery();
        BeanUtils.copyProperties(deliveryApiDto, delivery);
        return delivery;
    }
}
