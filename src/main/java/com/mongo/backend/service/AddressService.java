package com.mongo.backend.service;

import com.mongo.backend.mapper.AddressMapper;
import com.mongo.backend.model.api.account.AddressApiDto;
import com.mongo.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public Mono<AddressApiDto> saveOrUpdate(AddressApiDto addressApiDto){
        return addressRepository.save(AddressMapper.toEntity(addressApiDto)).map(AddressMapper::toApi);
    }
    public Flux<AddressApiDto> getAll(){
        return addressRepository.findAll().map(AddressMapper::toApi);
    }
    public Mono<AddressApiDto> findById(String id){
        return addressRepository.findById(id).map(AddressMapper::toApi);
    }
    public Mono<AddressApiDto> delete(String id){
        Mono<AddressApiDto> dtoMono = findById(id);
        return addressRepository.deleteById(id).thenReturn(Objects.requireNonNull(dtoMono.block()));
    }
}
