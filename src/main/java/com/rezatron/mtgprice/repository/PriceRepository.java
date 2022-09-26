package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public
interface PriceRepository extends MongoRepository<Price, String> {
    List<Price> findByTimestamp(
            @NonNull
            LocalDateTime timestamp);

    @Query( value = "select card_id from price where timestamp=?1",
            nativeQuery = true )
    public
    List<String> findCardIdsByTimestamp(
            @NonNull
            LocalDateTime timestamp);

}
