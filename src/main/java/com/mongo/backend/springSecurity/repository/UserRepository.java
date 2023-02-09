package com.mongo.backend.springSecurity.repository;

import com.mongo.backend.springSecurity.model.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User,String> {
    Mono<User> findByUsername(String username);
    Mono<User> save(String user);

}
