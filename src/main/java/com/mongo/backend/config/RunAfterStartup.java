package com.mongo.backend.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RunAfterStartup{
    @PostConstruct
    public void runAfterStartup() {
        System.out.println("Yaaah, I am running........");
//        new CommentService().CommentAuthorize_Auto();
    }
}
