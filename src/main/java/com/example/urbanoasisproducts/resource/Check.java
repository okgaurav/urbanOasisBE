package com.example.urbanoasisproducts.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Check {
    @GetMapping
    public ResponseEntity<String> checkApplication(){
        return ResponseEntity.ok("Hello World");
    }

}
