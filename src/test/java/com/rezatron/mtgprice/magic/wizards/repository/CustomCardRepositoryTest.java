package com.rezatron.mtgprice.magic.wizards.repository;

import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.repository.CardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
//@ExtendWith( SpringExtension.class )
public
class CustomCardRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CardRepository cardRepository;

    @AfterEach
    void cleanUpDatabase() {
        mongoTemplate.getDb().drop();
    }

    @DisplayName( "given card to save it is able to be saved" )
    @Test
    public
    void testBasicSave()
    {
        // given
        String cardId = "44623693-51d6-49ad-8cd7-140505caf02f";
        Card furySliver = Card.builder().id( cardId ).name( "Fury Sliver" ).typeLine( "Creature — Sliver" )
                              .cardTypes( Set.of( CardType.CREATURE ) ).cmc( "6" )
                              .oracleText( "All Sliver " + "creatures have " + "double strike." ).manaCost( "{5}{R}" )
                              .build();


        // when
        mongoTemplate.save( furySliver,
                            "cards" );
        Optional<Card> returnedCards = cardRepository.findById( cardId );

        // then
        assertFalse( returnedCards.isEmpty() );

        Card card = returnedCards.get();
//        assertEquals( furySliver,
//                      returnedCards.get() );
        assertEquals( cardId,
                      card.getId() );
        assertEquals( "Fury Sliver",
                      card.getName() );
        assertEquals( "Creature — Sliver",
                      card.getTypeLine() );
        assertEquals( "All Sliver creatures have double strike.",
                      card.getOracleText() );
        assertEquals( 1,
                      card.getCardTypes().size() );
        assertTrue( card.getCardTypes().contains( CardType.CREATURE ) );
        assertEquals( "6",
                      card.getCmc() );
        assertEquals( "{5}{R}",
                      card.getManaCost() );
    }


}
