package com.mongo.backend.repository;

import com.mongo.backend.model.entity.account.Address;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ReactiveMongoRepository<Address,String> {
}
