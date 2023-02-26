package com.mongo.backend.resource;

import com.mongo.backend.model.api.cart.UserCollectionApiDto;
import com.mongo.backend.service.UserCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
@RequestMapping("/v1/{userId}/collection")
public class UserCollectionResource {

    private final UserCollectionService userCollectionService;
    @Autowired
    public UserCollectionResource(UserCollectionService userCollectionService) {
        this.userCollectionService = userCollectionService;
    }

    @GetMapping
    public Flux<UserCollectionApiDto> FindAll(@RequestParam("userId") String userId){
        return userCollectionService.findAll(userId);
    }
}
