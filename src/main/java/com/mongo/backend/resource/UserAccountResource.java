package com.mongo.backend.resource;

import com.mongo.backend.model.api.account.UserAccountApiDto;
import com.mongo.backend.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/v1/user")
public class UserAccountResource {
    @Autowired
    private UserAccountService userAccountService;

    @PostMapping
    public Mono<UserAccountApiDto> Create(@RequestBody Mono<UserAccountApiDto> accountApiDtoMono) {
        return userAccountService.create(accountApiDtoMono);
    }
    @GetMapping("/all")
    public Flux<UserAccountApiDto> FindAll(){
        return userAccountService.findAll();
    }
    @GetMapping
    public Flux<UserAccountApiDto> FindAllAvailabe(){
        return userAccountService.findAllAvailable();
    }
    @GetMapping("/{id}")
    public Mono<UserAccountApiDto> FindById(@PathVariable("id") String id){
        return userAccountService.findById(id);
    }
}
