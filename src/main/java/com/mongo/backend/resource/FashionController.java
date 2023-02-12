package com.mongo.backend.resource;

import com.mongo.backend.model.api.StatusApiDto;
import com.mongo.backend.model.api.fashion.FashionApiDto;
import com.mongo.backend.service.FashionService;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/v1/fashion")
public class FashionController {

    @Autowired
    private FashionService fashionService;
    @GetMapping("/all")
    public Flux<FashionApiDto> FindAll(){
        return fashionService.findAll();
    }
    @PostMapping
    public Mono<FashionApiDto> Create(@RequestBody Mono<FashionApiDto> item) {
        return fashionService.create(item);
    }
    @GetMapping("/{id}")
    public Mono<FashionApiDto> FindById(@PathVariable("id") String id){
        return fashionService.findById(id);
    }
    @PatchMapping("/{id}")
    public Mono<FashionApiDto> UpdateProduct(@PathVariable("id") String id, @RequestBody Mono<FashionApiDto> item){
        return fashionService.update(id,item);
    }
    @PutMapping("/delete/{id}")
    public Mono<UpdateResult> DeleteQuantity(@PathVariable("id") String id){
        return fashionService.deleteProduct(id);
    }
    @PutMapping
    public Mono<UpdateResult> UpdateProductStatus(@RequestBody StatusApiDto statusApiDto){
        return fashionService.updateFashionProduct(statusApiDto.getUniqueId(),statusApiDto.getIsVisible());
    }

}
