package com.mongo.backend.repository;

import com.mongo.backend.model.api.cart.UserCollectionApiDto;
import com.mongo.backend.model.entity.account.UserAccount;
import com.mongo.backend.model.entity.cart.UserCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.replaceRoot;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@Slf4j
public class UserCollectionJavaRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public UserCollectionJavaRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<UserCollectionApiDto> save(UserCollectionApiDto userCollection) {
        var query = new Query().addCriteria(where("uniqueId").is(userCollection.getAccountId())
                .and("collections").elemMatch(where("collectionId").is(userCollection.getCollectionId())));
        var query2 = new Query().addCriteria(where("uniqueId").is(userCollection.getAccountId()));
        Update update = null;
        update = new Update()
                .set("collections.$.collectionTotal", userCollection.getCollectionTotal())
                .set("collections.$.coupanCode", userCollection.getCoupanCode())
                .set("collections.$.products", userCollection.getProducts())
                .set("collections.$.discount", userCollection.getDiscount());

        return mongoTemplate.updateFirst(query, update, UserAccount.class).flatMap(
                updateResult -> {
                    if (updateResult.getModifiedCount() > 0) {
                        return Mono.just(userCollection)
                                .doOnSuccess(s -> log.info("UserCollection Updated in Account with Id: {}", s.getAccountId()));
                    } else {
                        Update newUpdate = new Update().push("collections", userCollection);
                        return mongoTemplate.updateFirst(query2, newUpdate, UserAccount.class)
                                .thenReturn(userCollection)
                                .doOnSuccess(s -> log.info("Collection Added in Account with Id: {}", s.getAccountId()));
                    }
                }
        );
    }

    public Mono<UserCollection> delete(UserCollection userCollection) {
        var query = new Query().addCriteria(where("accountId").is(userCollection.getAccountId()));
        Update update = new Update().pull("collections", Query.query(where("collectionId").is(userCollection.getCollectionId())));
        return mongoTemplate.updateFirst(query, update, UserAccount.class)
                .thenReturn(userCollection)
                .doOnSuccess(s -> log.info("UserCollection Deleted from Account with Account Id: {}", s.getAccountId()));

    }

    public Flux<UserCollection> findAll(String userId) {
        var aggregation = newAggregation(
                match(where("uniqueId").is(userId)),
                unwind("collections"),
                replaceRoot("collections"));
        return mongoTemplate.aggregate(aggregation, UserAccount.class, UserCollection.class);
    }

    public Mono<UserCollection> findById(String accountId, String collectionId) {
        return findAll(accountId).filter(a -> a.getCollectionId().equals(collectionId)).next();
    }
    public Mono<UserCollection> moveToCart(UserCollection userCollection) {
        var query = new Query().addCriteria(where("uniqueId").is(userCollection.getAccountId()));
//        Update update = new Update().pull("collections", Query.query(where("collectionId").is(userCollection.getCollectionId())));

        var update2 = new Update().set("cart.$.collection",userCollection);
//        return mongoTemplate.updateFirst(query,update,UserAccount.class)
//                .flatMap(a-> mongoTemplate.updateFirst(query,update2,UserAccount.class)).thenReturn(userCollection);
        return  mongoTemplate.updateFirst(query,update2,UserAccount.class).thenReturn(userCollection);
    }

    public Mono<UserCollection> moveToCollection(UserCollection userCollection) {
        var query = new Query().addCriteria(where("uniqueId").is(userCollection.getAccountId()));
        var update = new Update().set("cart.$.collection",new UserCollection());
//        Update update2 = new Update().push("collections", Query.query(where("collectionId").is(userCollection.getCollectionId())));

//        return mongoTemplate.updateFirst(query,update,UserAccount.class)
//                .flatMap(a -> {
//                    log.info("Query 1", a.getModifiedCount());
//                    return mongoTemplate.updateFirst(query,update2,UserAccount.class);
//                }).thenReturn(userCollection);
        return mongoTemplate.updateFirst(query,update,UserAccount.class).thenReturn(userCollection);
    }
}
