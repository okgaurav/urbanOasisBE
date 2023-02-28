package com.mongo.backend.resource;

import com.mongo.backend.model.api.cart.UserCartApiDto;
import com.mongo.backend.service.CartService;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/v1/{accountId}/cart")
public class CartResource {
    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public Mono<UserCartApiDto> Find(@PathVariable("accountId") String accountId){
        return cartService.findById(accountId)
                .doOnSubscribe(s -> log.info("Finding UserCart with Id = {}",s));
    }
    @PatchMapping
    public Mono<UpdateResult> UpdateNsave(@PathVariable("accountId") String accountId,@RequestBody UserCartApiDto userCartApiDto){
        return cartService.saveOrUpdate(userCartApiDto,accountId)
                .doOnSubscribe(s -> log.info("Finding UserCart with Id = {}",userCartApiDto.getCartId()));
    }
    @DeleteMapping("/{id}")
    public Mono<UserCartApiDto> Delete(@PathVariable("accountId") String accountId, @PathVariable("id") String id){
        return cartService.delete(accountId, id)
                .doOnSubscribe(s -> log.info("Finding UserCart with Id = {}",s));
    }
}
