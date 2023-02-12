package com.mongo.backend.service;

import com.mongo.backend.mapper.FashionMapper;
import com.mongo.backend.model.api.fashion.FashionApiDto;
import com.mongo.backend.repository.FashionJavaRepository;
import com.mongo.backend.repository.FashionRepository;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FashionService {
    @Autowired
    private FashionRepository fashionRepository;

    @Autowired
    private FashionJavaRepository fashionJavaRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(FashionService.class);

    public Flux<FashionApiDto> findAll() {
        return fashionRepository.findAll().map(FashionMapper::toApi)
                .doOnSubscribe(s -> logger.debug("Searching Users within {}"))
                .doOnComplete(() -> logger.debug("Users within {} retrieved"));
    }
    public Mono<FashionApiDto> findById(String Id){
        return fashionRepository.findById(Id).map(FashionMapper :: toApi)
                .doOnSubscribe(s -> logger.debug("Searching User within {}"))
                .doOnNext(m -> logger.debug("Users within {} retrieved",m.getUniqueId()));
    }
    public Mono<FashionApiDto> create(Mono<FashionApiDto> item) {
        return item.map(FashionMapper::toEntity)
                .flatMap(fashionRepository::insert)
                .map(FashionMapper::toApi)
                .doOnSubscribe(s -> logger.info("Creating {}", item))
                .doOnNext(q -> logger.info("Created {}", q));
    }
    public  Mono<FashionApiDto>update(String id,Mono<FashionApiDto> item){
//        item.map((fashion) ->{
//            if(fashion!=null && fashion.getUniqueId() !=null){
//                return fashionRepository.
//            }
//         })
//        Utils.patch(fashionRepository.findById(id).map(FashionMapper::toApi).block(), item.block());

//    return fashionRepository.findById(id)
//                .map(FashionMapper::toApi)
//                .map((fashion) -> {
//                    Utils.patch(fashion, item.block());
//                    logger.debug("fashion {}",fashion);
//                    return fashion;
//                });
        return fashionRepository.findById(id)
                .flatMap(p->item.map(FashionMapper::toEntity)
                .doOnNext(e -> e.setUniqueId(id)))
                .flatMap(fashionRepository::save)
                .map(FashionMapper::toApi);
    }
    public Mono<UpdateResult> deleteProduct(String id){
        return fashionJavaRepository.updateQuantity(id,0);
    }
    public Mono<UpdateResult> updateFashionProduct(String id,Boolean status){
        return fashionJavaRepository.updateFashionProduct(id,status);
    }
}
