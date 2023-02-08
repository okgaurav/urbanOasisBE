package com.mongo.backend.springSecurity.service;


import java.util.Map;

import com.mongo.backend.springSecurity.model.User;
import com.mongo.backend.springSecurity.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * This is just an example, you can load the user from the database from the repository.
 * 
 */
@Service

public class UserService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);

    private Map<String, User> data;
    @Autowired
    private UserRepository userRepository;

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .doOnSubscribe(s -> logger.info("Finding User With Username {}", username))
                .doOnNext(q -> logger.info("Found: {}", q));
    }
    public Mono<User> saveUser(User user) {
        return userRepository.save(user)
                .doOnSubscribe(s -> logger.info("Creating {}", user))
                .doOnNext(q -> logger.info("Created {}", q));
    }

}
