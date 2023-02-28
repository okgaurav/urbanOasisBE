package com.mongo.backend.mapper;

import com.mongo.backend.model.api.cart.UserCollectionApiDto;
import com.mongo.backend.model.entity.cart.UserCollection;

import static com.mongo.backend.mapper.MapperUtils.checkNull;
import static com.mongo.backend.mapper.MapperUtils.mapListIfNotNull;
import static com.mongo.backend.model.utils.Utils.isNotNull;

public class UserCollectionMapper {
    public static UserCollectionApiDto toApi(UserCollection userCollection) {
        if (isNotNull(userCollection)) {
            UserCollectionApiDto userCollectionApiDto = new UserCollectionApiDto()
                    .setCollectionId(checkNull(userCollection.getCollectionId()))
                    .setCollectionTotal(checkNull(userCollection.getCollectionTotal()))
                    .setAccountId(checkNull(userCollection.getAccountId()))
                    .setProducts(mapListIfNotNull(FashionMapper::toApi,userCollection.getProducts()))
                    .setCoupanCode(checkNull(userCollection.getCoupanCode()))
                    .setDiscount(checkNull(userCollection.getDiscount()));
            return userCollectionApiDto;
        }
        return null;
    }

    public static UserCollection toEntity(UserCollectionApiDto userCollectionApiDto) {
        if (isNotNull(userCollectionApiDto)) {
            UserCollection userCollection = new UserCollection()
                    .setCollectionId(checkNull(userCollectionApiDto.getCollectionId()))
                    .setCollectionTotal(checkNull(userCollectionApiDto.getCollectionTotal()))
                    .setAccountId(checkNull(userCollectionApiDto.getAccountId()))
                    .setProducts(mapListIfNotNull(FashionMapper::toEntity,userCollectionApiDto.getProducts()))
                    .setDiscount(checkNull(userCollectionApiDto.getDiscount()))
                    .setCoupanCode(checkNull(userCollectionApiDto.getCoupanCode()));
            return userCollection;
        }
        return null;
    }
}
