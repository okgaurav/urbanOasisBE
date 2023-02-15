package com.mongo.backend.repository;

import com.mongo.backend.model.entity.Comments;
import com.mongo.backend.model.entity.account.UserAccount;
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
public class CommentJavaRepository {
    private final ReactiveMongoTemplate mongoTemplate;
    @Autowired
    public CommentJavaRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<Comments> add(UserAccount userAccount, Comments comments){
        var query = new Query().addCriteria(where("uniqueId").is(userAccount.getUniqueId()));
        var update = new Update().push("userComments",comments);
        //return mongoTemplate.upsert(query, update, "comments")
        return mongoTemplate.updateFirst(query, update, UserAccount.class)
                .thenReturn(comments).doOnSuccess(s-> log.info("Comment Added in Account with Id: {}",s.getAccountId()));
    }

}
