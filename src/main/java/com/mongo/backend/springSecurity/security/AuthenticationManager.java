package com.mongo.backend.springSecurity.security;

import com.mongo.backend.springSecurity.model.AuthenticationRequest;
import com.mongo.backend.springSecurity.model.AuthenticationResponse;
import com.mongo.backend.springSecurity.model.entity.User;
import com.mongo.backend.springSecurity.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongo.backend.springSecurity.model.entity.Role.ROLE_USER;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private  JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private UserRepository userRepository;
    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username = jwtUtil.getUsernameFromToken(authToken);
        return Mono.just(jwtUtil.validateToken(authToken))
            .filter(valid -> valid)
            .switchIfEmpty(Mono.empty())
            .map(valid -> {
                Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
                List<String> rolesMap = claims.get("role", List.class);
                return new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
            });
    }
    public AuthenticationResponse register(AuthenticationRequest request) {
        var user = new User();
                user.setUsername(request.getUsername());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setRoles(Arrays.asList(ROLE_USER));

        userRepository.save(user);
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
