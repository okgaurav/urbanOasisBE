package com.mongo.backend.service;

import com.mongo.backend.config.State;
import com.mongo.backend.mapper.FashionMapper;
import com.mongo.backend.model.api.fashion.FashionApiDto;
import com.mongo.backend.model.entity.Comments;
import com.mongo.backend.model.entity.fashion.Fashion;
import com.mongo.backend.repository.CommentJavaRepository;
import com.mongo.backend.repository.FashionJavaRepository;
import com.mongo.backend.repository.FashionRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FashionService {
    @Autowired
    private FashionRepository fashionRepository;
    @Autowired
    private CommentJavaRepository commentJavaRepository;
    @Autowired
    private FashionJavaRepository fashionJavaRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(FashionService.class);

    public Flux<FashionApiDto> findAll() {
        return fashionRepository.findAll().map(FashionMapper::toApi)
                .doOnSubscribe(s -> logger.debug("Searching Users within {}"))
                .doOnComplete(() -> logger.debug("Users within {} retrieved"));
    }

    public Mono<FashionApiDto> findById(String Id) {
        return fashionRepository.findById(Id).map(FashionMapper::toApi)
                .doOnSubscribe(s -> logger.debug("Searching User within {}"))
                .doOnNext(m -> logger.debug("Users within {} retrieved", m.getUniqueId()));
    }

    public Fashion setDefaults(Fashion fashion) {
        logger.info(fashion.toString());
        return fashion.setUniqueId(ObjectId.get().toHexString());
    }

    public Mono<FashionApiDto> create(Mono<FashionApiDto> item) {
        return item.map(FashionMapper::toEntity)
                .map(this::setDefaults)
                .flatMap(fashionRepository::save)
                .doOnSuccess(a -> logger.info("Successfully Created {}", a.getpId()))
                .map(FashionMapper::toApi)
                .doOnSubscribe(s -> logger.info("Creating {}", item))
                .doOnNext(q -> logger.info("Created {}", q));
    }

    public Mono<FashionApiDto> updateFashionProduct(FashionApiDto fashionApiDto) {
        return fashionJavaRepository.updateFashion(fashionApiDto);
    }

    public Flux<FashionApiDto> searchFashionProducts(String text) {
        return fashionJavaRepository.searchFashionProducts(text);
    }

    public Flux<FashionApiDto> findAllAvailable() {
        return fashionRepository.findAllByIsVisibleTrue();
    }

    public Mono<Comments> addComment(Comments com) {
        return fashionRepository.findById(com.getProductUniqueId())
                .flatMap(a -> fashionJavaRepository.add(a, com))
                .doOnSuccess(s -> logger.info("Comment Added with Id ={}", s.getUniqueId()));
    }
    public Mono<Comments> publishComment(Comments com){
        return fashionRepository.findById(com.getProductUniqueId())
                .flatMap(aa -> fashionJavaRepository.updateRating(aa,com))
                .flatMap(fashion -> commentJavaRepository.getComment(com.getAccountId(), com.getUniqueId()))
                .flatMap(this::publishCommentStatus);
    }
    private Mono<Comments> publishCommentStatus(Comments com){
        com.setState(State.PUBLISHED);
        return commentJavaRepository.updateStatus(com);
    }

    public Mono<Comments> updateComment(Comments com) {
        return com.getCommentText().equals("Cant Update") ?
                Mono.just(new Comments().setCommentText("Cant Update")) :
                fashionRepository.findById(com.getProductUniqueId())
                        .flatMap(fashion -> fashionJavaRepository.updateComment(fashion, com));

    }
}
