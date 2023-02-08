package com.mongo.backend.repository;

import com.mongo.backend.model.entity.fashion.Fashion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FashionRepository extends ReactiveMongoRepository<Fashion,String> {
}
