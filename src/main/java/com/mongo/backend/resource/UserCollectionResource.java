package com.mongo.backend.resource;

import com.mongo.backend.model.api.cart.UserCollectionApiDto;
import com.mongo.backend.service.UserCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<UserCollectionApiDto> FindAll(@PathVariable("userId") String userId){
        return userCollectionService.findAll(userId)
                .doOnSubscribe(s -> log.info("Finding All Collections Available"))
                .doOnComplete(() -> log.info("Found All Collections"));
    }
    @GetMapping("/{collectionId}")
    public Mono<UserCollectionApiDto> Find(@PathVariable("userId") String userId ,@PathVariable("collectionId") String collectionId){
        return userCollectionService.findById(userId,collectionId)
                .doOnSubscribe(s -> log.info("Finding Collection with Id = {}",s));
    }
    @PatchMapping
    public Mono<UserCollectionApiDto> UpdateNsave(@RequestBody UserCollectionApiDto UserCollectionApiDto){
        return userCollectionService.saveOrUpdate(UserCollectionApiDto)
                .doOnSubscribe(s -> log.info("Updating/Adding Collection with Id = {}",s));
    }
    @DeleteMapping("/{collectionId}")
    public Mono<UserCollectionApiDto> Delete(@PathVariable("userId") String userId ,@PathVariable("collectionId") String collectionId){
        return userCollectionService.delete(userId,collectionId)
                .doOnSubscribe(s -> log.info("Deleting Collection with Id = {}",s));
    }
    @GetMapping("/move/{collectionId}")
    public Mono<UserCollectionApiDto> MoveToCart(@PathVariable("userId") String userId ,@PathVariable("collectionId") String collectionId){
        return userCollectionService.moveToCart(userId,collectionId)
                .doOnSubscribe(s -> log.info("Moving Collection to Cart with Id = {}",s));
    }
    @GetMapping("/moveback/{collectionId}")
    public Mono<UserCollectionApiDto> MoveToCollection(@PathVariable("userId") String userId ,@PathVariable("collectionId") String collectionId){
        return userCollectionService.moveToCollection(userId,collectionId)
                .doOnSubscribe(s -> log.info("Moving Cart to Collection with Id = {}",s));
    }

}
