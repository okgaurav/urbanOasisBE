package com.mongo.backend.resource;

import com.mongo.backend.model.api.account.AddressApiDto;
import com.mongo.backend.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/v1/address")
public class AddressResource {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public Flux<AddressApiDto> FindAll(){
        return addressService.getAll()
                .doOnSubscribe(s -> log.info("Finding All Addresses Available"))
                .doOnComplete(() -> log.info("Finding All Addresses Available"));
    }
    @GetMapping("/{id}")
    public Mono<AddressApiDto> FindAll(@PathVariable("id") String id){
        return addressService.findById(id)
                .doOnSubscribe(s -> log.info("Finding Addresses with Id = {}",s));
    }
    @PostMapping
    public Mono<AddressApiDto> Save(@RequestBody AddressApiDto addressApiDto){
        return addressService.saveOrUpdate(addressApiDto)
                .doOnSubscribe(s -> log.info("Finding Addresses with Id = {}",s));
    }
    @PatchMapping
    public Mono<AddressApiDto> Update(@RequestBody AddressApiDto addressApiDto){
        return addressService.saveOrUpdate(addressApiDto)
                .doOnSubscribe(s -> log.info("Finding Addresses with Id = {}",s));
    }
    @DeleteMapping("/{id}")
    public Mono<AddressApiDto> Delete(@PathVariable("id") String id){
        return addressService.delete(id)
                .doOnSubscribe(s -> log.info("Finding Addresses with Id = {}",s));
    }
}
