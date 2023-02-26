package com.mongo.backend.resource;

import com.mongo.backend.model.api.cart.UserCartApiDto;
import com.mongo.backend.service.CartService;
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
@RequestMapping("/v1/cart")
public class CartResource {
    @Autowired
    private CartService cartService;

    @GetMapping
    public Flux<UserCartApiDto> FindAll(){
        return cartService.getAll()
                .doOnSubscribe(s -> log.info("Finding All UserCart Available"))
                .doOnComplete(() -> log.info("Finding All UserCart Available"));
    }
    @GetMapping("/{id}")
    public Mono<UserCartApiDto> Find(@PathVariable("id") String id){
        return cartService.findById(id)
                .doOnSubscribe(s -> log.info("Finding UserCart with Id = {}",s));
    }
    @PatchMapping
    public Mono<UserCartApiDto> UpdateNsave(@RequestBody UserCartApiDto userCartApiDto){
        return cartService.saveOrUpdate(userCartApiDto)
                .doOnSubscribe(s -> log.info("Finding UserCart with Id = {}",userCartApiDto.getCartId()));
    }
    @DeleteMapping("/{id}")
    public Mono<UserCartApiDto> Delete(@PathVariable("id") String id){
        return cartService.delete(id)
                .doOnSubscribe(s -> log.info("Finding UserCart with Id = {}",s));
    }
}
