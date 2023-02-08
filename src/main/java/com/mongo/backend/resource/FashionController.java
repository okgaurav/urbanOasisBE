package com.mongo.backend.resource;

import com.mongo.backend.model.entity.fashion.Fashion;
import com.mongo.backend.service.FashionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fashion")
public class FashionController {

    private FashionService fashionService;
    @GetMapping("/all")
    public Flux<Fashion> findAll(){
        return fashionService.findAll();
    }
    @PostMapping
    public Mono<Fashion> create(@RequestBody Fashion item) {
        return fashionService.create(item);
    }
}
