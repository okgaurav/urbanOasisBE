package com.mongo.backend.springSecurity.rest;

import com.mongo.backend.springSecurity.model.ResponseToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ResourceREST {

    @GetMapping("/resource/user")
    @PreAuthorize("hasRole('USER')")
    public Mono<ResponseEntity<ResponseToken>> user() {
        return Mono.just(ResponseEntity.ok(new ResponseToken("Content for user")));
    }

    @GetMapping("/resource/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<ResponseToken>> admin() {
        return Mono.just(ResponseEntity.ok(new ResponseToken("Content for admin")));
    }

    @GetMapping("/resource/user-or-admin")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<ResponseToken>> userOrAdmin() {
        return Mono.just(ResponseEntity.ok(new ResponseToken("Content for user or admin")));
    }
}
