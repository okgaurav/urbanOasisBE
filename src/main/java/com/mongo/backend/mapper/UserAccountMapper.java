package com.mongo.backend.mapper;

import com.mongo.backend.model.api.account.UserAccountApiDto;
import com.mongo.backend.model.entity.account.UserAccount;

import java.util.stream.Collectors;

import static com.mongo.backend.mapper.MapperUtils.checkNull;
import static com.mongo.backend.mapper.MapperUtils.mapIfNotNull;
import static com.mongo.backend.mapper.MapperUtils.mapListIfNotNull;

public class UserAccountMapper {
    public static UserAccountApiDto toApi(UserAccount userAccount){
        UserAccountApiDto userAccountApiDto =new UserAccountApiDto()
                .setUserComments(mapListIfNotNull(CommentsMapper::toApi,userAccount.getUserComments()))
                .setUserType(userAccount.getUserType())
                .setActive(checkNull(userAccount.isActive()))
                .setCart(mapIfNotNull(UserCartMapper::toApi,userAccount.getCart()))
                .setCollections(mapListIfNotNull(UserCollectionMapper::toApi,userAccount.getCollections()))
                .setAddresses(mapListIfNotNull(AddressMapper::toApi,userAccount.getAddresses()))
                .setPhoneNo(checkNull(userAccount.getPhoneNo()))
                .setUniqueId(checkNull(userAccount.getUniqueId()))
                .setProfileImage(checkNull(userAccount.getProfileImage()));
        return userAccountApiDto;
    }

    public static UserAccount toEntity(UserAccountApiDto userAccountApiDto){
        UserAccount userAccount=new UserAccount()
                .setUserComments(userAccountApiDto.getUserComments().stream().map(CommentsMapper::toEntity).collect(Collectors.toList()))
                .setUserType(userAccountApiDto.getUserType())
                .setActive(checkNull(userAccountApiDto.isActive()))
                .setUniqueId(checkNull(userAccountApiDto.getUniqueId()))
                .setCart(mapIfNotNull(UserCartMapper::toEntity,userAccountApiDto.getCart()))
                .setCollections(mapListIfNotNull(UserCollectionMapper::toEntity,userAccountApiDto.getCollections()))
                .setAddresses(mapListIfNotNull(AddressMapper::toEntity,userAccountApiDto.getAddresses()))
                .setPhoneNo(checkNull(userAccountApiDto.getPhoneNo()))
                .setProfileImage(checkNull(userAccountApiDto.getProfileImage()));
        return userAccount;
    }
}
