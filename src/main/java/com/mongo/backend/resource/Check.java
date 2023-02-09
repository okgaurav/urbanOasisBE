package com.mongo.backend.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Check {
    @GetMapping("ok/1")
    public ResponseEntity<String> checkApplication1(){
        return ResponseEntity.ok("Hello World -1");
    }
    @GetMapping("ok/2")
    public ResponseEntity<String> checkApplication2(){
        return ResponseEntity.ok("Hello World -2");
    }
    @GetMapping("ok/3")
    public ResponseEntity<String> checkApplication3(){
        return ResponseEntity.ok("Hello World -3");
    }
    @GetMapping("admin/1")
    public ResponseEntity<String> checkApplication4(){
        return ResponseEntity.ok("Hello World from Admin 1");
    }

}
