package com.mongo.backend.repository;

import com.mongo.backend.model.api.fashion.FashionApiDto;
import com.mongo.backend.model.entity.fashion.Fashion;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FashionRepository extends ReactiveMongoRepository<Fashion,String> {

    Flux<FashionApiDto> findAllByIsVisibleTrue();
}
