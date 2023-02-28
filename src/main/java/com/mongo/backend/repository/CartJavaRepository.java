package com.mongo.backend.repository;

import com.mongo.backend.model.entity.account.UserAccount;
import com.mongo.backend.model.entity.cart.UserCart;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@Slf4j
public class CartJavaRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    public CartJavaRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<UpdateResult> save(UserCart userCart, String accountId) {
//        var query = new Query().addCriteria(where("uniqueId").is(userCart.getAccountId())
//                .and("cart").elemMatch(where("cartId").is(userCart.getCartId())));
        var query2 = new Query().addCriteria(where("uniqueId").is(userCart.getAccountId()));
        Update update = null;
        update = new Update()
                .set("cart.$.cartId", userCart.getCartId())
                .set("cart.$.accountId", accountId)
                .set("cart.$.collection", userCart.getCollection())
                .set("cart.$.deliveryDetails", userCart.getDeliveryDetails())
                .set("cart.$.cartValue", userCart.getCartValue());

        return mongoTemplate.updateFirst(query2, update, UserAccount.class);

//        .flatMap(
//                updateResult -> {
//                    if (updateResult.getModifiedCount() > 0) {
//                        return Mono.just(userCart)
//                                .doOnSuccess(s -> log.info("UserCart Updated in Account with Id: {}", s.getAccountId()));
//                    } else {
//                        Update newUpdate = new Update().push("cart", userCart);
//                        return mongoTemplate.updateFirst(query2, newUpdate, UserAccount.class)
//                                .thenReturn(userCart)
//                                .doOnSuccess(s -> log.info("Address Added in Account with Id: {}", s.getAccountId()));
//                    }
//                }
//        );
    }

    public Mono<UserCart> delete(UserCart userCart) {
        var query = new Query().addCriteria(where("uniqueId").is(userCart.getAccountId()));
        Update update = new Update()
                .set("cart", null);
        return mongoTemplate.updateFirst(query, update, UserAccount.class)
                .thenReturn(userCart)
                .doOnSuccess(s -> log.info("UserCart Deleted from Account with Account Id: {}", s.getAccountId()));

    }

    public Mono<UserCart> findById(String accountId) {
        return mongoTemplate.findById(accountId, UserAccount.class).map(UserAccount::getCart);
    }
}
