package com.mongo.backend.repository;

import com.mongo.backend.model.api.fashion.FashionApiDto;
import com.mongo.backend.model.entity.fashion.Fashion;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@Slf4j
public class FashionJavaRepository{
    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public FashionJavaRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    public Mono<UpdateResult> updateQuantity(String uniqueId, Integer units){
        var query = new Query().addCriteria(where("uniqueId").is(uniqueId));
        Update update = new Update();
        update.set("units",units);
        log.debug("Update:"+update.toString());
        return mongoTemplate.updateFirst(query, update, Fashion.class);
    }

    public Mono<UpdateResult> updateFashionProduct(String uniqueId, Boolean status) {
        var query = new Query().addCriteria(where("uniqueId").is(uniqueId));
        Update update = new Update();
        update.set("isVisible",status);
        log.debug("Update:"+update.toString());
        return mongoTemplate.updateFirst(query, update, Fashion.class);
    }
}
