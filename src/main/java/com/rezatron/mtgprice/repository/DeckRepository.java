package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.Deck;
import org.springframework.data.mongodb.repository.MongoRepository;

public
interface DeckRepository extends MongoRepository<Deck, String> {
}
