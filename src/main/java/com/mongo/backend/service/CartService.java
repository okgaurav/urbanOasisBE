package com.mongo.backend.service;

import com.mongo.backend.mapper.UserCartMapper;
import com.mongo.backend.model.api.cart.UserCartApiDto;
import com.mongo.backend.repository.CartJavaRepository;
import com.mongo.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartJavaRepository cartJavaRepository;
    @Autowired
    public CartService(CartRepository cartRepository, CartJavaRepository cartJavaRepository) {
        this.cartRepository = cartRepository;
        this.cartJavaRepository = cartJavaRepository;
    }
    
    public Mono<UserCartApiDto> saveOrUpdate(UserCartApiDto userCartApiDto){
        return cartRepository.save(UserCartMapper.toEntity(userCartApiDto)).flatMap(cartJavaRepository::save).map(UserCartMapper::toApi);
    }
    public Flux<UserCartApiDto> getAll(){
        return cartRepository.findAll().map(UserCartMapper::toApi);
    }
    public Mono<UserCartApiDto> findById(String id){
        return cartRepository.findById(id).map(UserCartMapper::toApi);
    }
    public Mono<UserCartApiDto> delete(String id){
        Mono<UserCartApiDto> dtoMono = findById(id);
        return dtoMono.flatMap(addressApiDto -> cartJavaRepository.delete(UserCartMapper.toEntity(addressApiDto)))
                .flatMap(address -> cartRepository.deleteById(id))
                .thenReturn(Objects.requireNonNull(dtoMono.block()));
    }
}
