package com.mongo.backend.service;

import com.mongo.backend.error.AccountValidationException;
import com.mongo.backend.mapper.CommentsMapper;
import com.mongo.backend.mapper.UserAccountMapper;
import com.mongo.backend.model.api.CommentsApiDto;
import com.mongo.backend.model.entity.Comments;
import com.mongo.backend.model.entity.account.UserAccount;
import com.mongo.backend.model.utils.Utils;
import com.mongo.backend.repository.CommentJavaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentJavaRepository commentJavaRepository;
    @Autowired
    private UserAccountService accountService;
    public Mono<CommentsApiDto> create(CommentsApiDto item) {
         return accountService.findById(item.getAccountId())
                 .map(UserAccountMapper::toEntity)
                 .doOnSubscribe(a ->log.info("Creating a Comment for accountId=[{}]",item.getAccountId()))
                 .flatMap(a-> {
                     try {
                         return validateAndPost(a, CommentsMapper.toEntity(item));
                     } catch (AccountValidationException e) {
                         throw new RuntimeException(e);
                     }
                 })
                 .doOnSuccess(s -> log.debug("Created a comment in userId=[{}].", s.getAccountId()))
                 .map(CommentsMapper::toApi);
    }

    private Mono<Comments> validateAndPost(UserAccount a, Comments item) throws AccountValidationException {
        log.info("Validating Account is Active or Not. id=[{}]", a.getUniqueId());
        item.setDateTime(Utils.getCurrentDateTime());
        if(a.isActive()!=true)
            throw new AccountValidationException("Account is Not Active");
        log.info("Account Validated id=[{}]", a.getUniqueId());
        return Mono.just(item).flatMap(comment->commentJavaRepository.add(a, comment));
    }
}
