package com.mongo.backend.repository;

import com.mongo.backend.model.entity.Comments;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comments,String> {
}
