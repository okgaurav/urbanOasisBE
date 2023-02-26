package com.mongo.backend.repository;

import com.mongo.backend.model.entity.cart.UserCollection;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class UserCollectionJavaRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public UserCollectionJavaRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Flux<UserCollection> findAll(String userId) {
//        var query = new Query().addCriteria(where("uniqueId").is(userId)
//                .and("comments").elemMatch(where("uniqueId").is(comment.getUniqueId())));
        return Flux.just(new UserCollection());
    }
}
