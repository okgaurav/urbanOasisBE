package com.mongo.backend.mapper;

import com.mongo.backend.model.api.account.AddressApiDto;
import com.mongo.backend.model.entity.account.Address;
import org.springframework.beans.BeanUtils;

public class AddressMapper {
    public static AddressApiDto toApi(Address address) {
        AddressApiDto addressApiDto = new AddressApiDto();
        BeanUtils.copyProperties(address, addressApiDto);
        return addressApiDto;
    }

    public static Address toEntity(AddressApiDto addressApiDto) {
        Address address = new Address();
        BeanUtils.copyProperties(addressApiDto, address);
        return address;
    }
}
