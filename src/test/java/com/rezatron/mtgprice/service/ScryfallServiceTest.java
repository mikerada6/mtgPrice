package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith( MockitoExtension.class )
class ScryfallServiceTest {

    @InjectMocks
    ScryfallService scryfallService;


    @Test
    void cargetCardFromJSON() throws IOException {
        ClassPathResource staticDataResource = new ClassPathResource( "twoCard.json");
        String json = IOUtils.toString( staticDataResource.getInputStream(), StandardCharsets.UTF_8);
        List<ScryfallCard> cards = scryfallService.getCardsFromJSON( json );
        assertNotNull( cards );
        assertEquals( 2,
                      cards.size() );
        ScryfallCard card = cards.get( 0 );
        assertNotNull( card );

        assertEquals( "card",
                      card.getObject() );
        assertEquals( "0000579f-7b35-4ed3-b44c-db2a538066fe",
                      card.getId() );
        assertEquals( "44623693-51d6-49ad-8cd7-140505caf02f",
                      card.getOracleId() );
        assertEquals( 1,
                      card.getMultiverseIds().size() );
        assertEquals( 109722,
                      card.getMultiverseIds().get( 0 ) );
        assertEquals( 25527,
                      card.getMtgoId() );
        assertEquals( 25528,
                      card.getMtgoFoilId() );
        assertEquals( 14240,
                      card.getTcgplayerId() );
        assertEquals( 13850,
                      card.getCardMarketId() );
        assertEquals( "Fury Sliver",
                      card.getName() );
        assertEquals( "en",
                      card.getLanguage() );
        assertEquals( "2006-10-06",
                      card.getReleasedAt() );
        assertEquals( "https://api.scryfall.com/cards/0000579f-7b35-4ed3-b44c-db2a538066fe",
                      card.getUri() );
        assertEquals( "https://scryfall.com/card/tsp/157/fury-sliver?utm_source=api",
                      card.getScryfallUri() );
        assertEquals( "normal",
                      card.getLayout() );
        assertEquals( true,
                      card.getHighresImage() );
        assertEquals( "highres_scan",
                      card.getImageStatus() );
        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/small/front/0/0/0000579f-7b35-4ed3-b44c"
                      + "-db2a538066fe.jpg?1562894979",
                      card.getImageUris().getSmall() );
        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/normal/front/0/0/0000579f-7b35-4ed3-b44c"
                      + "-db2a538066fe.jpg?1562894979",
                      card.getImageUris().getNormal() );
        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/large/front/0/0/0000579f-7b35-4ed3-b44c"
                      + "-db2a538066fe.jpg?1562894979",
                      card.getImageUris().getLarge() );
        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/png/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe"
                      + ".png?1562894979",
                      card.getImageUris().getPng() );
        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/art_crop/front/0/0/0000579f-7b35-4ed3-b44c"
                      + "-db2a538066fe.jpg?1562894979",
                      card.getImageUris().getArtCrop() );
        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/border_crop/front/0/0/0000579f-7b35-4ed3-b44c"
                      + "-db2a538066fe.jpg?1562894979",
                      card.getImageUris().getBorderCrop() );
        assertEquals( "{5}{R}",
                      card.getManaCost() );
        assertEquals( "6",
                      card.getCmc() );
        assertEquals( "Creature — Sliver",
                      card.getTypeLine() );
        assertEquals( "All Sliver creatures have double strike.",
                      card.getOracleText() );
        assertEquals( "3",
                      card.getPower() );
        assertEquals( "3",
                      card.getToughness() );
        assertEquals( 1,
                      card.getColors().size() );
        assertEquals( "R",
                      card.getColors().get( 0 ) );
        assertEquals( 1,
                      card.getColorIdentity().size() );
        assertEquals( "R",
                      card.getColorIdentity().get( 0 ) );
        assertEquals( 0,
                      card.getKeywords().size() );
        assertEquals( "en",
                      card.getLanguage() );

        assertEquals( "c1d109bc-ffd8-428f-8d7d-3f8d7e648046",
                      card.getSetId() );
        assertEquals( "tsp",
                      card.getSet() );
        assertEquals( "Time Spiral",
                      card.getSetName() );
        assertEquals( "https://api.scryfall.com/sets/c1d109bc-ffd8-428f-8d7d-3f8d7e648046",
                      card.getSetUri() );
        assertEquals( "uncommon",
                      card.getRarity() );
        assertEquals( "157",
                      card.getCollectorNumber() );
        assertEquals( .29,
                      card.getPrices().getUsd() );
        assertEquals( 4.50,
                      card.getPrices().getUsdFoil() );
        assertEquals( null,
                      card.getPrices().getUsdEtched() );
        assertEquals( .23,
                      card.getPrices().getEur() );
        assertEquals( .50,
                      card.getPrices().getEurFoil() );
        assertEquals( .02,
                      card.getPrices().getTix() );
    }
}


