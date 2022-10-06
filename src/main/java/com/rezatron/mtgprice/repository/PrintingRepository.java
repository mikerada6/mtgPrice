package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.wizards.Printing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface PrintingRepository extends MongoRepository<Printing, String> {

    @Query( "{printings._id:'?0'}" )
    Object findByPrintingId(String printingId);

    @Query( "{id:'?0'}" )
    Optional<Printing> findById(String id);
}
