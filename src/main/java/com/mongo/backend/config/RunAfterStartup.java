package com.mongo.backend.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.mongo.backend.model.utils.Filter.loadConfigs;

@Component
public class RunAfterStartup{
    @PostConstruct
    public void runAfterStartup() {
        System.out.println("Yaaah, I am running........");
//        new CommentService().CommentAuthorize_Auto();
    }
}
