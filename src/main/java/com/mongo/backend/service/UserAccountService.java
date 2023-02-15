package com.mongo.backend.service;

import com.mongo.backend.mapper.UserAccountMapper;
import com.mongo.backend.model.api.account.UserAccountApiDto;
import com.mongo.backend.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public Mono<UserAccountApiDto> create(Mono<UserAccountApiDto> account) {
        return account.map(UserAccountMapper::toEntity)
                .flatMap(userAccountRepository::insert)
                .map(UserAccountMapper::toApi)
                .doOnSubscribe(s -> log.info("Creating Account {}",s.toString()))
                .doOnNext(q -> log.info("Created {}", q));
    }

    public Flux<UserAccountApiDto> findAllAvailable() {
        return  userAccountRepository.findAllByIsActiveTrue()
                .map(UserAccountMapper::toApi)
                .doOnSubscribe(s -> log.debug("Searching Users within {}"))
                .doOnComplete(() -> log.debug("Users within {} retrieved"));
    }

    public Flux<UserAccountApiDto> findAll() {
        return userAccountRepository.findAll().map(UserAccountMapper::toApi)
                .doOnSubscribe(s -> log.debug("Searching Users within {}"))
                .doOnComplete(() -> log.debug("Users within {} retrieved"));
    }
    public Mono<UserAccountApiDto> findById(String Id){
        return userAccountRepository.findById(Id).map(UserAccountMapper:: toApi)
                .doOnSubscribe(s -> log.debug("Searching User within {}"))
                .doOnNext(m -> log.debug("Users within {} retrieved",m.getUniqueId()));
    }
}
