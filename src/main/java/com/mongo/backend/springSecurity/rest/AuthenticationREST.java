package com.mongo.backend.springSecurity.rest;


import com.mongo.backend.springSecurity.model.AuthenticationRequest;
import com.mongo.backend.springSecurity.model.AuthenticationResponse;
import com.mongo.backend.springSecurity.model.User;
import com.mongo.backend.springSecurity.model.Role;
import com.mongo.backend.springSecurity.security.AuthenticationManager;
import com.mongo.backend.springSecurity.security.JWTUtil;
import com.mongo.backend.springSecurity.security.PBKDF2Encoder;

import com.mongo.backend.springSecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Arrays;


@AllArgsConstructor
@RestController
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/v1/login")
    public Mono<ResponseEntity<AuthenticationResponse>> login(@RequestBody AuthenticationRequest ar) {
        return userService.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
            .map(userDetails -> ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails))))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
//    @PostMapping("/signup")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest request
//    ) {
//        return ResponseEntity.ok(authenticationManager.register(request));
//    }
    @PostMapping("/v1/register")
    public Mono<ResponseEntity<AuthenticationResponse>> signup(@RequestBody AuthenticationRequest ar) {
        var user = new User();
        user.setUsername(ar.getUsername());
        user.setPassword(passwordEncoder.encode(ar.getPassword()));
        user.setRoles(Arrays.asList(Role.ROLE_USER));

        return userService.saveUser(user)
                .map(userDetails -> ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
}
}
