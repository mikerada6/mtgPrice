package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.wizards.OracleCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public
interface OracleCardRepository extends MongoRepository<OracleCard, String> {
}
