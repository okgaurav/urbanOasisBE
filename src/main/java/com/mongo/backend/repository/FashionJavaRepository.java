package com.mongo.backend.repository;

import com.mongo.backend.mapper.FashionMapper;
import com.mongo.backend.model.api.fashion.FashionApiDto;
import com.mongo.backend.model.entity.fashion.Fashion;
import com.mongo.backend.model.utils.Utils;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.matchingDocumentStructure;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@Slf4j
public class FashionJavaRepository{
    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public FashionJavaRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Flux<FashionApiDto> searchFashionProducts(String text) {
        var query = new Query().addCriteria(
                where("productName").exists(true)
                        .orOperator(where("tags").regex(text))
        );
        return mongoTemplate.find(query,Fashion.class).distinct().map(FashionMapper::toApi);
    }
    public Mono<FashionApiDto> updateFashion(FashionApiDto data){
        var query = new Query().addCriteria(where("uniqueId").is(data.getUniqueId()));
        Update update = Utils.patch(data);
        log.debug("Update:"+update.toString());
        return mongoTemplate.updateFirst(query, update, Fashion.class).thenReturn(data);
    }
}
