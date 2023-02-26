package com.mongo.backend.service;

import com.mongo.backend.mapper.UserCollectionMapper;
import com.mongo.backend.model.api.cart.UserCollectionApiDto;
import com.mongo.backend.repository.UserCollectionJavaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
                .doOnSubscribe(s -> log.info("Finding Collection with Id {}",s.toString()))
                .doOnNext(q -> log.info("Found Collection {}", q));
    }
}
