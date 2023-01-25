package com.example.urbanoasisproducts.security.Resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.urbanoasisproducts.security.Resources.ResourceConstants.AUTHORIZATION_DEMO;

@RestController
@RequestMapping(AUTHORIZATION_DEMO)
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
