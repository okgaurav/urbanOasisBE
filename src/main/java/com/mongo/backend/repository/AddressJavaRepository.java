package com.mongo.backend.repository;

import com.mongo.backend.model.entity.account.Address;
import com.mongo.backend.model.entity.account.UserAccount;
import com.mongo.backend.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@Slf4j
public class AddressJavaRepository {

    private final ReactiveMongoTemplate mongoTemplate;
    private final UserAccountService userAccountService;

    @Autowired
    public AddressJavaRepository(ReactiveMongoTemplate mongoTemplate, UserAccountService userAccountService) {
        this.mongoTemplate = mongoTemplate;
        this.userAccountService = userAccountService;
    }

    public Mono<Address> save(Address address) {
        var query = new Query().addCriteria(where("uniqueId").is(address.getAccountId())
                .and("addresses").elemMatch(where("addressId").is(address.getAddressId())));
        var query2 = new Query().addCriteria(where("uniqueId").is(address.getAccountId()));
        Update update = null;
        update = new Update()
                .set("addresses.$.houseNo", address.getHouseNo())
                .set("addresses.$.street", address.getStreet())
                .set("addresses.$.addressLine1", address.getAddressLine1())
                .set("addresses.$.addressLine2", address.getAddressLine2())
                .set("addresses.$.landMark", address.getLandMark())
                .set("addresses.$.pinCode", address.getPinCode())
                .set("addresses.$.state", address.getState())
                .set("addresses.$.country", address.getCountry())
                .set("addresses.$.phoneNumber", address.getPhoneNumber());
        return mongoTemplate.updateFirst(query, update, UserAccount.class).flatMap(
                updateResult -> {
                    if (updateResult.getModifiedCount() > 0) {
                        return Mono.just(address)
                                .doOnSuccess(s -> log.info("Address Updated in Account with Id: {}", s.getAccountId()));
                    } else {
                        Update newUpdate = new Update().push("addresses", address);
                        return mongoTemplate.updateFirst(query2, newUpdate, UserAccount.class)
                                .thenReturn(address)
                                .doOnSuccess(s -> log.info("Address Added in Account with Id: {}", s.getAccountId()));
                    }
                }
        );
    }
    public Mono<Address> delete(Address address) {
        var query = new Query().addCriteria(where("uniqueId").is(address.getAccountId()));
        Update update = new Update().pull("addresses", Query.query(where("addressId").is(address.getAddressId())));
        return mongoTemplate.updateFirst(query,update,UserAccount.class)
                .thenReturn(address)
                .doOnSuccess(s -> log.info("Address Deleted from Account with Address Id: {}", s.getAccountId()));
    }
}
