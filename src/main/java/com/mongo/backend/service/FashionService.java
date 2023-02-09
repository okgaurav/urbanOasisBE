package com.mongo.backend.service;

import com.mongo.backend.model.entity.fashion.Fashion;
import com.mongo.backend.repository.FashionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FashionService {
    private FashionRepository fashionRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FashionService.class);

    public Flux<Fashion> findAll(){
        return fashionRepository.findAll()
                .doOnSubscribe(s -> logger.debug("Searching Users within {}"))
                .doOnComplete(() -> logger.debug("Users within {} retrieved"));
    }
    public Mono<Fashion> create(Fashion item) {
        return  fashionRepository.save(item)
                .doOnSubscribe(s -> logger.info("Creating {}", item))
                .doOnNext(q -> logger.info("Created {}", q));
    }
}
