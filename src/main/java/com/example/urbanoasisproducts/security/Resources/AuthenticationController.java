package com.example.urbanoasisproducts.security.Resources;

import com.example.urbanoasisproducts.security.dao.AuthenticationRequest;
import com.example.urbanoasisproducts.security.dao.AuthenticationResponse;
import com.example.urbanoasisproducts.security.dao.RegisterRequest;
import com.example.urbanoasisproducts.security.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.urbanoasisproducts.security.Resources.ResourceConstants.*;

@RestController
@RequestMapping(SECURE_APP)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping(USER_REGISTER)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping(USER_AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}
