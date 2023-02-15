package com.mongo.backend.mapper;

import com.mongo.backend.model.api.account.UserAccountApiDto;
import com.mongo.backend.model.entity.account.UserAccount;
import org.springframework.beans.BeanUtils;

public class UserAccountMapper {
    public static UserAccountApiDto toApi(UserAccount userAccount){
        UserAccountApiDto userAccountApiDto =new UserAccountApiDto();
        BeanUtils.copyProperties(userAccount, userAccountApiDto);
        return userAccountApiDto;
    }

    public static UserAccount toEntity(UserAccountApiDto userAccountApiDto){
        UserAccount userAccount=new UserAccount();
        BeanUtils.copyProperties(userAccountApiDto,userAccount);
        return userAccount;
    }
}
