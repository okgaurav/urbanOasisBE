package com.mongo.backend.mapper;

import com.mongo.backend.model.api.cart.UserCartApiDto;
import com.mongo.backend.model.entity.cart.UserCart;
import org.springframework.beans.BeanUtils;

public class UserCartMapper {
    public static UserCartApiDto toApi(UserCart userCart) {
        UserCartApiDto userCartApiDto = new UserCartApiDto();
        BeanUtils.copyProperties(userCart, userCartApiDto);
        return userCartApiDto;
    }

    public static UserCart toEntity(UserCartApiDto userCartApiDto) {
        UserCart userCart = new UserCart();
        BeanUtils.copyProperties(userCartApiDto, userCart);
        return userCart;
    }
}
