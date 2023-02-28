package com.mongo.backend.mapper;

import com.mongo.backend.model.api.cart.UserCartApiDto;
import com.mongo.backend.model.entity.cart.UserCart;

import static com.mongo.backend.mapper.MapperUtils.checkNull;
import static com.mongo.backend.mapper.MapperUtils.mapIfNotNull;
import static com.mongo.backend.model.utils.Utils.isNotNull;

public class UserCartMapper {
    public static UserCartApiDto toApi(UserCart userCart) {
        if(isNotNull(userCart)) {
            UserCartApiDto userCartApiDto = new UserCartApiDto()
                    .setCartId(userCart.getCartId())
                    .setCartValue(checkNull(userCart.getCartValue()))
                    .setAccountId(userCart.getAccountId())
                    .setDeliveryDetails(mapIfNotNull(DeliveryMapper::toApi,userCart.getDeliveryDetails()))
                    .setCollection(mapIfNotNull(UserCollectionMapper::toApi,userCart.getCollection()));
            return userCartApiDto;
        }
        return null;
    }

    public static UserCart toEntity(UserCartApiDto userCartApiDto) {
        if(isNotNull(userCartApiDto)) {
            UserCart userCart = new UserCart()
                    .setCartId(userCartApiDto.getCartId())
                    .setCartValue(checkNull(userCartApiDto.getCartValue()))
                    .setAccountId(userCartApiDto.getAccountId())
                    .setDeliveryDetails(mapIfNotNull(DeliveryMapper::toEntity,userCartApiDto.getDeliveryDetails()))
                    .setCollection(mapIfNotNull(UserCollectionMapper::toEntity,userCartApiDto.getCollection()));
            return userCart;
        }
        return null;
    }
}
