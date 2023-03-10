package com.mongo.backend.resource;

import com.mongo.backend.model.api.CommentsApiDto;
import com.mongo.backend.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/v1/comments")
public class CommentResource {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public Mono<CommentsApiDto> Create(@RequestBody CommentsApiDto item) {
        return commentService.create(item);
    }
    @PutMapping
    public Mono<CommentsApiDto> Update(@RequestBody CommentsApiDto item){
        return commentService.update(item);
    }
    @GetMapping("/{accountId}")
    public Flux<CommentsApiDto> FindAll(@PathVariable("accountId") String item){
        return commentService.findAll(item);
    }
    @GetMapping
    public Mono<CommentsApiDto> GetComment(@RequestBody CommentsApiDto item) {
        return commentService.getComment(item.getAccountId(),item.getUniqueId());
    }
    @GetMapping("/publish")
    public Mono<CommentsApiDto> publishComment(@RequestBody CommentsApiDto item){
        return commentService.updateState(item);
    }
}
