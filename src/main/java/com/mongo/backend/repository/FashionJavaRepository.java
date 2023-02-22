package com.mongo.backend.repository;

import com.mongo.backend.mapper.FashionMapper;
import com.mongo.backend.model.api.fashion.FashionApiDto;
import com.mongo.backend.model.entity.Comments;
import com.mongo.backend.model.entity.fashion.Fashion;
import com.mongo.backend.model.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@Slf4j
public class FashionJavaRepository {
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
        return mongoTemplate.find(query, Fashion.class).distinct().map(FashionMapper::toApi);
    }

    public Mono<FashionApiDto> updateFashion(FashionApiDto data) {
        var query = new Query().addCriteria(where("uniqueId").is(data.getUniqueId()));
        Update update = Utils.patch(data);
        log.debug("Update:" + update.toString());
        return mongoTemplate.updateFirst(query, update, Fashion.class).thenReturn(data);
    }
    public Mono<Fashion> updateRating(Fashion data,Comments comments){
        var query = new Query().addCriteria(where("uniqueId").is(data.getUniqueId()));
        var ratingIndex = comments.getRating()-1;
        var ratingList = data.getRating();
        ratingList.set(ratingIndex,ratingList.get(ratingIndex)+1);
        data.setRating(ratingList);
        Update update = new Update().set("rating",ratingList);
        log.debug("Update:" + update.toString());
        return mongoTemplate.updateFirst(query, update, Fashion.class).thenReturn(data);
    }
    public Mono<Fashion> dropRating(Fashion data,Comments oldComment){
        var query = new Query().addCriteria(where("uniqueId").is(oldComment.getAccountId()));
        var ratingList = data.getRating();
        var oldRatingIndex = oldComment.getRating()-1;
        log.info("Fashion Retrived {}",data.getUniqueId());
        ratingList.set(oldRatingIndex,ratingList.get(oldRatingIndex)-1);
        data.setRating(ratingList);
        Update update = new Update().set("rating",ratingList);

        var removeDataQuery = new Query().addCriteria(where("uniqueId").is(data.getUniqueId())
                .and("comments").elemMatch(where("uniqueId").is(oldComment.getUniqueId())));
        return mongoTemplate.updateFirst(query, update, Fashion.class).thenReturn(data);
    }
    public Mono<Comments> add(Fashion fashion, Comments comments) {
        var query = new Query().addCriteria(where("uniqueId").is(fashion.getUniqueId()));
        var update = new Update().push("comments", comments);
        //return mongoTemplate.upsert(query, update, "comments")
        return mongoTemplate.updateFirst(query, update, Fashion.class)
                .thenReturn(comments).doOnSuccess(s -> log.info("Comment Added in Account with Id: {}", s.getAccountId()));
    }

    public Mono<Comments> updateComment(Fashion fashion, Comments comment) {
        var query = new Query().addCriteria(where("uniqueId").is(fashion.getUniqueId())
                .and("comments").elemMatch(where("uniqueId").is(comment.getUniqueId())));
        var update = new Update()
                .set("comments.$.commentText", comment.getCommentText())
                .set("comments.$.rating", comment.getRating())
                .set("comments.$.version", comment.getVersion())
                .set("comments.$.dateTime", comment.getDateTime())
                .set("comments.$.images", comment.getImages());
        return mongoTemplate.updateFirst(query, update, Fashion.class)
                .thenReturn(comment).doOnSuccess(s -> log.info("Comment Updated in Account with Id: {}", s.getAccountId()));
    }

}
