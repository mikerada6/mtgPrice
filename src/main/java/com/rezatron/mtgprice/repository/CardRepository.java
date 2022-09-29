package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.wizards.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public
interface CardRepository extends MongoRepository<Card, String> {

    @Query("{id:'?0'}")
    Optional<Card> findById(String id);

}
