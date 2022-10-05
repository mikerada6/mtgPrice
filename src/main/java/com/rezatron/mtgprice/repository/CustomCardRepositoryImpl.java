package com.rezatron.mtgprice.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.UpdateResult;
import com.rezatron.mtgprice.dto.PriceUpdate;
import com.rezatron.mtgprice.entity.Price;
import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.entity.wizards.Printing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public
class CustomCardRepositoryImpl implements CustomCardRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public
    boolean addPriceToCard(String cardId, String printingId, Price price) {
        Update update = new Update();
        Query query = new Query( Criteria.where( "id" ).is( cardId ).and( "printings._id" ).is( printingId ) );
        update.addToSet( "printings.$.prices",
                         price );
        UpdateResult returnValue = mongoTemplate.updateFirst( query,
                                                              update,
                                                              Card.class );
        return returnValue.getModifiedCount() > 0;
    }

    @Override
    public
    boolean  addPricesToCard(List<PriceUpdate> priceUpdateList) {
        BulkOperations ops = mongoTemplate.bulkOps( BulkOperations.BulkMode.UNORDERED,
                                                    Card.class );
        for (PriceUpdate p : priceUpdateList) {
            Update update = new Update();
            update.addToSet( "printings.$.prices",
                             p.getPrice() );
            Query query = new Query( Criteria.where( "id" ).is( p.getCardId() ).and( "printings._id" )
                                             .is( p.getPrintingId() ) );
            ops.updateOne( query,
                           update );
        }
        BulkWriteResult returnValue = ops.execute();
        return returnValue.getModifiedCount() == priceUpdateList.size();
    }

    @Override
    public
    boolean addPrintingToCard(String cardId, Printing printing) {
        Update update = new Update();

        Query query = new Query( Criteria.where( "id" ).is( cardId ) );
        update.addToSet( "printings",
                         printing );
        mongoTemplate.update( Printing.class ).matching( query ).apply( update ).first();
        UpdateResult returnValue = mongoTemplate.updateFirst( query,
                                                              update,
                                                              Card.class );
        return returnValue.getModifiedCount() > 0;
    }

    @Override
    public
    boolean doesCardExist(String cardId) {
        Query query = new Query( Criteria.where( "_id" ).is( cardId ) );
        long count = mongoTemplate.count( query,
                                          "cards" );
        return count > 0;
    }

    @Override
    public
    boolean doesPrintingExist(String printingId) {
        Query query = new Query( Criteria.where( "printings._id" ).is( printingId ) );
        long count = mongoTemplate.count( query,
                                          "cards" );
        return count > 0;
    }

    @Override
    public
    boolean doesPriceExist(String priceId) {
        Query query = new Query( Criteria.where( "printings.prices._id" ).is( priceId ) );
        long count = mongoTemplate.count( query,
                                          "cards" );
        return count > 0;
    }

    @Override
    public
    List<String> findIdsNotInDatabase(List<String> ids) {
        Query query = new Query( Criteria.where( "_id" ).in( ids ) );
        query.fields().include("_id");
        List<Card> list = mongoTemplate.find(query, Card.class);
        List<String> idsInDataBase = mongoTemplate.find( query,
                                                         Card.class ).stream().map( c -> c.getId() )
                                                  .collect( Collectors.toList() );
        return ids.stream().filter(id -> !idsInDataBase.contains( id )).collect( Collectors.toList());
    }

    @Override
    public
    List<String> findPrintingIdsNotInDatabase(List<String> ids) {
        Query query = new Query( Criteria.where( "printings._id" ).in( ids ) );
        query.fields().include("printings._id");
        List<Card> list = mongoTemplate.find(query, Card.class);
        List<String> idsInDataBase = mongoTemplate.find( query,
                                                         Card.class ).stream()
                                                  .flatMap( c -> c.getPrintings().stream().map( p -> p.getId() ) )
                                                  .collect( Collectors.toList() );
        return ids.stream().filter(id -> !idsInDataBase.contains( id )).collect( Collectors.toList());
    }


}
