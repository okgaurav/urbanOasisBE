package com.mongo.backend.repository;

import com.mongo.backend.model.entity.account.UserAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface UserAccountRepository extends ReactiveMongoRepository<UserAccount,String> {
    Flux<UserAccount> findAllByIsActiveTrue();
    Flux<UserAccount>findAll();

}
