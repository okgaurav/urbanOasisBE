package com.mongo.backend.service;

import com.mongo.backend.config.State;
import com.mongo.backend.error.AccountValidationException;
import com.mongo.backend.mapper.CommentsMapper;
import com.mongo.backend.mapper.UserAccountMapper;
import com.mongo.backend.model.api.CommentsApiDto;
import com.mongo.backend.model.entity.Comments;
import com.mongo.backend.model.entity.account.UserAccount;
import com.mongo.backend.model.utils.Utils;
import com.mongo.backend.repository.CommentJavaRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.mongo.backend.config.State.RESERVED;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentJavaRepository commentJavaRepository;
    @Autowired
    private UserAccountService accountService;
    public Comments setDefaults(Comments comments, State state,Integer version){
        return comments.setState(state).setVersion(version).setUniqueId(comments.getUniqueId()==null?ObjectId.get().toHexString(): comments.getUniqueId());
    }
    public Mono<CommentsApiDto> create(CommentsApiDto item) {
         return  accountService.findById(item.getAccountId())
                 .map(UserAccountMapper::toEntity)
                 .doOnSubscribe(a ->log.info("Creating a Comment for accountId=[{}]",item.getAccountId()))
                 .flatMap(a-> {
                     try {
                         return validateAndPost(a,setDefaults(CommentsMapper.toEntity(item),RESERVED,0),true);
                     } catch (AccountValidationException e) {
                         throw new RuntimeException(e);
                     }
                 })
                 .doOnSuccess(s -> log.debug("Created a comment in userId=[{}].", s.getAccountId()))
                 .map(CommentsMapper::toApi);
    }

    private Mono<Comments> validateAndPost(UserAccount a, Comments item,boolean post) throws AccountValidationException {
        log.info("Validating Account is Active or Not. id=[{}]", a.getUniqueId());
        item.setDateTime(Utils.getCurrentDateTime());
        if(a.isActive()!=true)
            throw new AccountValidationException("Account is Not Active");
        log.info("Account Validated id=[{}]", a.getUniqueId());
        return post ? Mono.just(item).flatMap(comment->commentJavaRepository.add(a, comment)):
                Mono.just(item).flatMap(comment->commentJavaRepository.update(a, comment));
    }

    public Mono<CommentsApiDto> update(CommentsApiDto item) {
        return accountService.findById(item.getAccountId())
                .map(UserAccountMapper::toEntity)
                .doOnSubscribe(a ->log.info("Updating a Comment for accountId=[{}]",item.getAccountId()))
                .flatMap(a-> {
                    try {
                        return validateAndPost(a, setDefaults(CommentsMapper.toEntity(item),RESERVED, item.getVersion()+1),false);
                    } catch (AccountValidationException e) {
                        throw new RuntimeException(e);
                    }
                })
                .doOnSuccess(s -> log.debug("Created a comment in userId=[{}].", s.getAccountId()))
                .map(CommentsMapper::toApi);
    }
}
