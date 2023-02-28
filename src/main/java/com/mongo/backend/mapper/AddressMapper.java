package com.mongo.backend.mapper;

import com.mongo.backend.model.api.account.AddressApiDto;
import com.mongo.backend.model.entity.account.Address;

import static com.mongo.backend.mapper.MapperUtils.checkNull;
import static com.mongo.backend.model.utils.Utils.isNotNull;

public class AddressMapper {
    public static AddressApiDto toApi(Address address) {
        if(isNotNull(address)) {
            AddressApiDto addressApiDto = new AddressApiDto()
                    .setAddressId(address.getAddressId())
                    .setAddressLine1(checkNull(address.getAddressLine1()))
                    .setAddressLine2(checkNull(address.getAddressLine2()))
                    .setHouseNo(checkNull(address.getHouseNo()))
                    .setStreet(checkNull(address.getStreet()))
                    .setLandMark(checkNull(address.getLandMark()))
                    .setCountry(checkNull(address.getCountry()))
                    .setAccountId(checkNull(address.getAccountId()))
                    .setPinCode(checkNull(address.getPinCode()))
                    .setPhoneNumber(checkNull(address.getPhoneNumber()))
                    .setState(checkNull(address.getState()));
            return addressApiDto;
        }
        return null;
    }

    public static Address toEntity(AddressApiDto addressApiDto) {
        if(isNotNull(addressApiDto)){
            Address address = new Address()
                    .setAddressId(addressApiDto.getAddressId())
                    .setAddressLine1(checkNull(addressApiDto.getAddressLine1()))
                    .setAddressLine2(checkNull(addressApiDto.getAddressLine2()))
                    .setHouseNo(checkNull(addressApiDto.getHouseNo()))
                    .setStreet(checkNull(addressApiDto.getStreet()))
                    .setLandMark(checkNull(addressApiDto.getLandMark()))
                    .setCountry(checkNull(addressApiDto.getCountry()))
                    .setAccountId(checkNull(addressApiDto.getAccountId()))
                    .setPinCode(checkNull(addressApiDto.getPinCode()))
                    .setPhoneNumber(checkNull(addressApiDto.getPhoneNumber()))
                    .setState(checkNull(addressApiDto.getState()));
            return address;
        }
        return null;
    }
}
