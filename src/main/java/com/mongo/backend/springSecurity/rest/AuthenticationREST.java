package com.mongo.backend.springSecurity.rest;


import com.mongo.backend.springSecurity.model.AuthenticationRequest;
import com.mongo.backend.springSecurity.model.AuthenticationResponse;
import com.mongo.backend.springSecurity.model.SignupRequest;
import com.mongo.backend.springSecurity.model.entity.User;
import com.mongo.backend.springSecurity.security.AuthenticationManager;
import com.mongo.backend.springSecurity.security.JWTUtil;

import com.mongo.backend.springSecurity.security.PBKDF2Encoder;
import com.mongo.backend.springSecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static com.mongo.backend.springSecurity.model.entity.Role.ROLE_USER;


@AllArgsConstructor
@RestController
@Slf4j
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/v1/login")
    public Mono<ResponseEntity<AuthenticationResponse>> login(@RequestBody AuthenticationRequest ar) {
        return userService.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
            .map(userDetails -> ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken((User) userDetails))))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
    @PostMapping("/v1/register")
    public Mono<ResponseEntity<AuthenticationResponse>> signup(@RequestBody SignupRequest ar) {
        var user = new User();
        var userName = ar.getEmail().substring(0,ar.getEmail().indexOf("@"));

        user.setUsername(userName);
        user.setEmail(ar.getEmail());
        user.setDepartment(ar.getDepartment());
        user.setPassword(passwordEncoder.encode(ar.getPassword()));
        user.setRoles(Arrays.asList(ROLE_USER));
        return userService.saveUser(user)
                .map(userDetails -> ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
//    public List<Role> setRoleUsingDepartment(Department department){
//        List<Role> roleList = Arrays.asList(USER);
//        if(SALES.name().equals(department.name()))
//            roleList.add(SALE);
//        if(IT.name().equals(department.name()))
//            roleList.add(DEVELOPER);
//        log.debug(roleList.toString());
//        return (roleList);
//    }
}
