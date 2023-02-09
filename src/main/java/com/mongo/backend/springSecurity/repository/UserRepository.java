package com.mongo.backend.springSecurity.repository;

import com.mongo.backend.springSecurity.model.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User,String> {
    Mono<UserDetails> findByUsername(String username);
    Mono<User> save(String user);

}
