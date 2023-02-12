package com.mongo.backend.mapper;

import com.mongo.backend.model.entity.fashion.Fashion;
import com.mongo.backend.model.api.fashion.FashionApiDto;
import org.springframework.beans.BeanUtils;

public class FashionMapper {
    public static FashionApiDto toApi(Fashion fashion){
        FashionApiDto fashionApiDto =new FashionApiDto();
        BeanUtils.copyProperties(fashion, fashionApiDto);
        return fashionApiDto;
    }

    public static Fashion toEntity(FashionApiDto fashionApiDto){
        Fashion fashion =new Fashion();
        BeanUtils.copyProperties(fashionApiDto,fashion);
        return fashion;
    }
}
