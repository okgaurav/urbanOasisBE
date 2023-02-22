package com.mongo.backend.service;

import com.mongo.backend.config.State;
import com.mongo.backend.error.AccountValidationException;
import com.mongo.backend.mapper.CommentsMapper;
import com.mongo.backend.mapper.UserAccountMapper;
import com.mongo.backend.model.api.CommentsApiDto;
import com.mongo.backend.model.api.account.UserAccountApiDto;
import com.mongo.backend.model.entity.Comments;
import com.mongo.backend.model.entity.account.UserAccount;
import com.mongo.backend.model.utils.Time;
import com.mongo.backend.model.utils.Utils;
import com.mongo.backend.repository.CommentJavaRepository;
import com.mongo.backend.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

import static com.mongo.backend.config.State.RESERVED;
import static com.mongo.backend.model.ModelConstants.DATE_TIME_FORMAT;

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
    public Comments updateDefault(Comments comments, Integer version){
        if(comments.getState() == RESERVED)
            return comments.setVersion(version);
        return new Comments().setCommentText("Cant Update");
    }
    public Comments setDefaults(Comments comments, State state,Integer version){
        return comments.setState(state).setVersion(version).setUniqueId(ObjectId.get().toHexString());
    }
    public Mono<CommentsApiDto> create(CommentsApiDto item) {
         return  accountService.findById(item.getAccountId())
                 .map(UserAccountMapper::toEntity)
                 .doOnSubscribe(a ->log.info("Creating a Comment for accountId=[{}]",item.getAccountId()))
                 .flatMap(a-> {
                     try {
                         return validateAndPost(a,setDefaults(CommentsMapper.toEntity(item),RESERVED,0),true);
                     } catch (AccountValidationException e) {
                         return Mono.error(new RuntimeException(e));
                     }
                 })
                 .doOnSuccess(s -> log.debug("Created a comment in userId=[{}].", s.getAccountId()))
                     .flatMap(com -> fashionService.addComment(com))
                     .doOnSuccess(s -> log.debug("Added comment in Fashion Product=[{}].", s.getProductUniqueId()))
                 .map(CommentsMapper::toApi);
    }

    private Mono<Comments> validateAndPost(UserAccount a, Comments item,boolean post) throws AccountValidationException {
        log.info("Validating Account is Active or Not. id=[{}] ans State ={}", a.getUniqueId(),item.getState());
        item.setDateTime(Utils.getCurrentDateTime());
        if(a.isActive()!=true)
            throw new AccountValidationException("Account is Not Active");
        log.info("Account Validated id=[{}]", a.getUniqueId());
        return post ? Mono.just(item).flatMap(comment->commentJavaRepository.add(a, comment)):
                Mono.just(item).flatMap(this::updateRatingFashion);
    }
    private Mono<Comments> updateRatingFashion(Comments comment){
        return commentJavaRepository.update(comment);
    }
    public Mono<CommentsApiDto> update(CommentsApiDto item) {
        return accountService.findById(item.getAccountId())
                .map(UserAccountMapper::toEntity)
                .doOnSubscribe(a ->log.info("Updating a Comment for accountId=[{}] and State ={}", item.getAccountId(), item.getState()))
                .flatMap(a-> {
                    try {
                        return validateAndPost(a,updateDefault(CommentsMapper.toEntity(item), item.getVersion()+1),false);
                    } catch (AccountValidationException e) {
                        return Mono.error(new RuntimeException(e));
                    }
                })
                .doOnSuccess(s -> log.debug("Updated a comment in userId=[{}].", s.getAccountId()))
                .flatMap(com -> fashionService.updateComment(com))
                .doOnSuccess(s -> log.debug("Added comment in Fashion Product=[{}].", s.getProductUniqueId()))
                .map(CommentsMapper::toApi);
    }
    public void CommentAuthorize_Auto() {
        Flux<UserAccountApiDto> allAccount = accountService.findAll()
                .map(account -> (UserAccountApiDto) UserAccountMapper.toEntity(account).getUserComments().stream().filter(comments ->
                                Time.getCommonDifference(comments.getDateTime(), Time.getCurrentDateTime(), DATE_TIME_FORMAT, TimeUnit.MINUTES) > 5)
                        .map(comments -> comments.setState(State.PUBLISHED)).map(comment -> this.update(CommentsMapper.toApi(comment)))
                );
    }


    public Flux<CommentsApiDto> findAll(String item) {
        return commentJavaRepository.findAll(item).map(CommentsMapper::toApi);
    }


    public Mono<CommentsApiDto> getComment(String accountId, String commentId) {
        return commentJavaRepository.getComment(accountId,commentId)
                .doOnSubscribe(s-> log.info("Finding Comment with Id ={}",commentId))
                .doOnSuccess(s-> log.info("Found Comment with Id ={}",commentId))
                .map(CommentsMapper::toApi);
    }
}
