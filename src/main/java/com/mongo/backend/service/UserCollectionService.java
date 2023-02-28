package com.mongo.backend.service;

import com.mongo.backend.mapper.UserCollectionMapper;
import com.mongo.backend.model.api.cart.UserCollectionApiDto;
import com.mongo.backend.repository.UserCollectionJavaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
public class UserCollectionService {

    private final UserCollectionJavaRepository userCollectionJavaRepository;

    @Autowired
    public UserCollectionService(UserCollectionJavaRepository userCollectionJavaRepository) {
        this.userCollectionJavaRepository = userCollectionJavaRepository;
    }

    public Flux<UserCollectionApiDto> findAll(String userId) {
        return userCollectionJavaRepository.findAll(userId)
                .map(UserCollectionMapper::toApi)
                .doOnSubscribe(s -> log.info("Finding Collection with Id {}", s.toString()))
                .doOnNext(q -> log.info("Found Collection {}", q))
                .switchIfEmpty(onNotFound(HttpStatus.NOT_FOUND,"No Collection Found"));
    }
    private Mono<UserCollectionApiDto> onNotFound(HttpStatus status,String text) {
        return Mono.error(new ResponseStatusException(status, text));
    }
    public Mono<UserCollectionApiDto> saveOrUpdate(UserCollectionApiDto userCollectionApiDto) {
        return userCollectionJavaRepository.save(userCollectionApiDto);
    }

    public Mono<UserCollectionApiDto> findById(String accountId, String collectionId) {
        return userCollectionJavaRepository.findById(accountId, collectionId)
                .map(UserCollectionMapper::toApi)
                .switchIfEmpty(onNotFound(HttpStatus.NOT_FOUND,"Unable to Retrieve Collection"));
    }
    public Mono<UserCollectionApiDto> delete(String accountId, String collectionId) {
        Mono<UserCollectionApiDto> dtoMono = findById(accountId, collectionId);
        return dtoMono.flatMap(userCollectionApiDto -> userCollectionJavaRepository.delete(UserCollectionMapper.toEntity(userCollectionApiDto)))
                .thenReturn(Objects.requireNonNull(dtoMono.block()));
    }

    public Mono<UserCollectionApiDto> moveToCart(String userId, String collectionId) {
        return findById(userId, collectionId)
                .flatMap(coll -> userCollectionJavaRepository.moveToCart(UserCollectionMapper.toEntity(coll)))
                .map(UserCollectionMapper::toApi)
                .switchIfEmpty(onNotFound(HttpStatus.BAD_REQUEST,"Unable to Move Collection To Cart"));
    }

    public Mono<UserCollectionApiDto> moveToCollection(String userId, String collectionId) {
        return findById(userId, collectionId)
                .flatMap(coll -> userCollectionJavaRepository.moveToCollection(UserCollectionMapper.toEntity(coll)))
                .map(UserCollectionMapper::toApi)
                .switchIfEmpty(onNotFound(HttpStatus.BAD_REQUEST,"Unable to Move Cart to Collection"));
    }
}
