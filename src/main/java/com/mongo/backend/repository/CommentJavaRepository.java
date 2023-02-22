package com.mongo.backend.repository;

import com.mongo.backend.model.entity.Comments;
import com.mongo.backend.model.entity.account.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Mono<Comments> update(Comments comment) {
        var query = new Query().addCriteria(where("uniqueId").is(comment.getAccountId())
                .and("userComments").elemMatch(where("uniqueId").is(comment.getUniqueId())));
        var update = new Update()
                .set("userComments.$.commentText", comment.getCommentText())
                .set("userComments.$.rating", comment.getRating())
                .set("userComments.$.version",comment.getVersion())
                .set("userComments.$.dateTime",comment.getDateTime())
                .set("userComments.$.images", comment.getImages());
        return mongoTemplate.updateFirst(query, update, UserAccount.class)
                .thenReturn(comment).doOnSuccess(s-> log.info("Comment Updated in Account with Id: {}",s.getAccountId()));
    }
    public Flux<Comments> findAll(String accountId) {
//        var filter = where("activeReservations.reservationStatus").is(RESERVED);
        var aggregation = newAggregation(
                match(where("uniqueId").is(accountId)),
                unwind("userComments "),
                replaceRoot("userComments "));
        return mongoTemplate.aggregate(aggregation, UserAccount.class, Comments.class);
    }

    public Mono<Comments> getComment(String accountId, String commentId) {
        var query = new Query().addCriteria(where("uniqueId").is(accountId)
                .and("userComments").elemMatch(where("uniqueId").is(commentId)));
        log.info("Comment Retrived {}",accountId);
        return mongoTemplate.findOne(query,Comments.class)
                .doOnSuccess(s-> log.info("Found Comment with Id ={}",commentId));
    }
}
