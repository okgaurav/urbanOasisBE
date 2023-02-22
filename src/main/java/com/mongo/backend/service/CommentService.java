package com.mongo.backend.service;

import com.mongo.backend.config.State;
import com.mongo.backend.mapper.CommentsMapper;
import com.mongo.backend.mapper.UserAccountMapper;
import com.mongo.backend.model.api.CommentsApiDto;
import com.mongo.backend.model.api.account.UserAccountApiDto;
import com.mongo.backend.model.api.fashion.FashionApiDto;
import com.mongo.backend.model.entity.Comments;
import com.mongo.backend.model.utils.Utils;
import com.mongo.backend.repository.CommentJavaRepository;
import com.mongo.backend.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.mongo.backend.config.State.PUBLISHED;
import static com.mongo.backend.config.State.RESERVED;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentJavaRepository commentJavaRepository;
    @Autowired
    private FashionService fashionService;

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserAccountService accountService;
//    public Mono<Comments> updateDefault(Comments comments){
//        try{
//            return getOriginalComment(comments);
//        }catch (Exception e){
//            log.error("Cant Update any Published Comment");
//        }
//        return Mono.just(new Comments().setCommentText("Cant Update any Published Comment"));
//    }
    private Mono<Comments> getOriginalComment(Comments comments){
        return commentJavaRepository.getComment(comments.getAccountId(),comments.getUniqueId())
                .flatMap(comments1 -> this.StateCheck(comments1, comments));
    }
    public Mono<Comments> StateCheck(Comments comments,Comments newComment){
        if(comments.getState()== PUBLISHED)
            throw new RuntimeException("Comment Already Published");
        newComment.setVersion(comments.getVersion()+1);
        return Mono.just(newComment);
    }
    public Comments setDefaults(Comments comments, State state,Integer version){
        return comments.setState(state).setVersion(version).setUniqueId(ObjectId.get().toHexString());
    }
//    public Mono<CommentsApiDto> create_(CommentsApiDto item) {
//         return  accountService.findById(item.getAccountId())
//                 .map(UserAccountMapper::toEntity)
//                 .doOnSubscribe(a ->log.info("Creating a Comment for accountId=[{}]",item.getAccountId()))
//                 .flatMap(a-> {
//                     try {
//                         return validateAndPost(a,setDefaults(CommentsMapper.toEntity(item),RESERVED,0),true);
//                     } catch (AccountValidationException e) {
//                         return Mono.error(new RuntimeException(e));
//                     }
//                 })
//                 .doOnSuccess(s -> log.debug("Created a comment in userId=[{}].", s.getAccountId()))
//                     .flatMap(com -> fashionService.addComment(com))
//                     .doOnSuccess(s -> log.debug("Added comment in Fashion Product=[{}].", s.getProductUniqueId()))
//                 .map(CommentsMapper::toApi);
//    }

//    private Mono<Comments> validateAndPost(UserAccount a, Comments item,boolean post) throws AccountValidationException {
//        log.info("Validating Account is Active or Not. id=[{}] ans State ={}", a.getUniqueId(),item.getState());
//        item.setDateTime(Utils.getCurrentDateTime());
//        if(a.isActive()!=true)
//            throw new AccountValidationException("Account is Not Active");
//        log.info("Account Validated id=[{}]", a.getUniqueId());
//        return post ? Mono.just(item).flatMap(comment->commentJavaRepository.add(a, comment)):
//                updateFashionComment(item).map(CommentsMapper::toEntity);
//    }
//    private Mono<CommentsApiDto> updateFashionComment(Comments comment){
//        return commentJavaRepository.update(comment).flatMap(com -> fashionService.updateComment(com)).map(CommentsMapper::toApi);
//    }

    public Flux<CommentsApiDto> findAll(String item) {
        return commentJavaRepository.findAll(item).map(CommentsMapper::toApi);
    }


    public Mono<CommentsApiDto> getComment(String accountId, String commentId) {
        return commentJavaRepository.getComment(accountId,commentId)
                .doOnSubscribe(s-> log.info("Finding Comment with Id ={}",commentId))
                .doOnSuccess(s-> log.info("Found Comment with Id ={}",commentId))
                .map(CommentsMapper::toApi);
    }

    public Mono<CommentsApiDto> updateState(CommentsApiDto commentsApiDto) {
        return commentJavaRepository.updateStatus(CommentsMapper.toEntity(commentsApiDto))
                .flatMap(comments->fashionService.publishFashionComment(CommentsMapper.toEntity(commentsApiDto)))
                .map(CommentsMapper::toApi);
    }

//    ---------------------------------------------Update New Code ----------------------------------------------------

    private Mono<UserAccountApiDto> accountValidation(CommentsApiDto comment) {
        return accountService.findById(comment.getAccountId())
                .doOnSuccess(e -> log.info("Account Fetched with id ={}", comment.getAccountId()))
                .doOnError(e -> log.error("Account Fetch processing failed for accountId={}.", comment.getAccountId()))
                .switchIfEmpty(onAccountNotFound());
    }

    private Mono<UserAccountApiDto> onAccountNotFound() {
        return Mono.error(new RuntimeException("Account not found"));
    }

    private Mono<FashionApiDto> productValidation(CommentsApiDto comment) {
        return fashionService.findById(comment.getProductUniqueId())
                .doOnSuccess(e -> log.info("Product Fetched with id ={}", comment.getProductUniqueId()))
                .doOnError(e -> log.error("Product Fetch processing failed for accountId={}.", comment.getProductUniqueId()))
                .switchIfEmpty(onProductNotFound());
    }

    private Mono<FashionApiDto> onProductNotFound() {
        return Mono.error(new RuntimeException("Product not found"));
    }

    private Mono<CommentsApiDto> getSavedComment(CommentsApiDto comments) {
        return commentJavaRepository.getComment(comments.getAccountId(), comments.getUniqueId()).map(CommentsMapper::toApi)
                .doOnSuccess(e -> log.info("Comment Fetched with id ={}", comments.getUniqueId()))
                .doOnError(e -> log.error("Comment Fetch processing failed for accountId={}.", comments.getUniqueId()))
                .switchIfEmpty(onCommentNotFound());
    }
    public Mono<CommentsApiDto> StateCommentCheck(CommentsApiDto comments,CommentsApiDto newComment){
        if(comments.getState().equals(PUBLISHED))
            throw new RuntimeException("Comment Already Published");
        newComment.setVersion(comments.getVersion()+1);
        newComment.setDateTime(Utils.getCurrentDateTime());
        return Mono.just(newComment);
    }

    private Mono<CommentsApiDto> onCommentNotFound() {
        return Mono.error(new RuntimeException("Comment not found"));
    }
    private Mono<CommentsApiDto> updateFashionComment_(Comments comment){
        return commentJavaRepository.update(comment).flatMap(com -> fashionService.updateComment(com)).map(CommentsMapper::toApi);
    }
    public Mono<CommentsApiDto> update(CommentsApiDto commentsApiDto) {
        return accountValidation(commentsApiDto)
                .flatMap(account -> productValidation(commentsApiDto))
                .flatMap(fashion -> getSavedComment(commentsApiDto))
                .flatMap(comm -> StateCommentCheck(comm,commentsApiDto))
                .flatMap(newComm -> updateFashionComment_(CommentsMapper.toEntity(newComm)));
    }
//    ---------------------------------------------Create New Code ----------------------------------------------------
    private Mono<CommentsApiDto> setDefault(CommentsApiDto commentsApiDto){
        commentsApiDto.setVersion(0);
        commentsApiDto.setDateTime(Utils.getCurrentDateTime());
        commentsApiDto.setState(RESERVED);
        commentsApiDto.setUniqueId(ObjectId.get().toHexString());
        return Mono.just(commentsApiDto);
    }
    private Mono<CommentsApiDto> sendCreateRequest(CommentsApiDto commentsApiDto){
        return accountValidation(commentsApiDto)
                .flatMap(accountApiDto -> commentJavaRepository.add(UserAccountMapper.toEntity(accountApiDto),CommentsMapper.toEntity(commentsApiDto)))
                .map(CommentsMapper::toApi);
    }
    public Mono<CommentsApiDto> create(CommentsApiDto commentsApiDto) {
        return accountValidation(commentsApiDto)
                .flatMap(account -> productValidation(commentsApiDto))
                .flatMap(comm -> setDefault(commentsApiDto))
                .flatMap(this::sendCreateRequest)
                .flatMap(com -> fashionService.addComment(CommentsMapper.toEntity(com)))
                .map(CommentsMapper::toApi);
    }
}
