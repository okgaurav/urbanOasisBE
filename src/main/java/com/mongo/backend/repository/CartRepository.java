package com.mongo.backend.repository;

import com.mongo.backend.model.entity.cart.UserCart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends ReactiveMongoRepository<UserCart,String> {
}
