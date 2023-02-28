package com.mongo.backend.service;

import com.mongo.backend.mapper.UserCartMapper;
import com.mongo.backend.model.api.cart.UserCartApiDto;
import com.mongo.backend.repository.CartJavaRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CartService {
    private final CartJavaRepository cartJavaRepository;
    @Autowired
    public CartService(CartJavaRepository cartJavaRepository) {
        this.cartJavaRepository = cartJavaRepository;
    }
    
    public Mono<UpdateResult> saveOrUpdate(UserCartApiDto userCartApiDto,String accountId){
        return cartJavaRepository.save(UserCartMapper.toEntity(userCartApiDto),accountId);//map(UserCartMapper::toApi);
    }
    public Mono<UserCartApiDto> findById(String accountId){
        return cartJavaRepository.findById(accountId).map(UserCartMapper::toApi)
                .switchIfEmpty(onNotFound(HttpStatus.NOT_FOUND,"Unable to retrieve UserCart"));
    }
    public Mono<UserCartApiDto> delete(String accountId, String id){
        Mono<UserCartApiDto> dtoMono = findById(accountId);
        return dtoMono.flatMap(addressApiDto -> cartJavaRepository.delete(UserCartMapper.toEntity(addressApiDto)))
                .thenReturn(Objects.requireNonNull(dtoMono.block()));
    }
    private Mono<UserCartApiDto> onNotFound(HttpStatus status,String text) {
        return Mono.error(new ResponseStatusException(status, text));
    }
}
