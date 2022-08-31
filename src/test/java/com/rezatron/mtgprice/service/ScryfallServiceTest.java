package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.magic.scryfall.ScryfallCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith( MockitoExtension.class )
class ScryfallServiceTest {

    @InjectMocks
    ScryfallService scryfallService;


//    @Test
//    void cargetCardFromJSON() {
//        String json = """
//                   {"object":"card",
//                   "id":"0000579f-7b35-4ed3-b44c-db2a538066fe",
//                   "oracle_id":"44623693-51d6-49ad-8cd7-140505caf02f",
//                   "multiverse_ids":[
//                      109722
//                   ],
//                   "mtgo_id":25527,
//                   "mtgo_foil_id":25528,
//                   "tcgplayer_id":14240,
//                   "cardmarket_id":13850,
//                   "name":"Fury Sliver",
//                   "lang":"en",
//                   "released_at":"2006-10-06",
//                   "uri":"https://api.scryfall.com/cards/0000579f-7b35-4ed3-b44c-db2a538066fe",
//                   "scryfall_uri":"https://scryfall.com/card/tsp/157/fury-sliver?utm_source=api",
//                   "layout":"normal",
//                   "highres_image":true,
//                   "image_status":"highres_scan",
//                   "image_uris":{
//                      "small":"https://c1.scryfall.com/file/scryfall-cards/small/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979",
//                      "normal":"https://c1.scryfall.com/file/scryfall-cards/normal/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979",
//                      "large":"https://c1.scryfall.com/file/scryfall-cards/large/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979",
//                      "png":"https://c1.scryfall.com/file/scryfall-cards/png/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.png?1562894979",
//                      "art_crop":"https://c1.scryfall.com/file/scryfall-cards/art_crop/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979",
//                      "border_crop":"https://c1.scryfall.com/file/scryfall-cards/border_crop/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979"
//                   },
//                   "mana_cost":"{5}{R}",
//                   "cmc":6.0,
//                   "type_line":"Creature — Sliver",
//                   "oracle_text":"All Sliver creatures have double strike.",
//                   "power":"3",
//                   "toughness":"4",
//                   "colors":[
//                      "R"
//                   ],
//                   "color_identity":[
//                      "R"
//                   ],
//                   "keywords":[
//                     \s
//                   ],
//                   "legalities":{
//                      "standard":"not_legal",
//                      "future":"not_legal",
//                      "historic":"not_legal",
//                      "gladiator":"not_legal",
//                      "pioneer":"not_legal",
//                      "explorer":"not_legal",
//                      "modern":"legal",
//                      "legacy":"legal",
//                      "pauper":"not_legal",
//                      "vintage":"legal",
//                      "penny":"legal",
//                      "commander":"legal",
//                      "brawl":"not_legal",
//                      "historicbrawl":"not_legal",
//                      "alchemy":"not_legal",
//                      "paupercommander":"restricted",
//                      "duel":"legal",
//                      "oldschool":"not_legal",
//                      "premodern":"not_legal"
//                   },
//                   "games":[
//                      "paper",
//                      "mtgo"
//                   ],
//                   "reserved":false,
//                   "foil":true,
//                   "nonfoil":true,
//                   "finishes":[
//                      "nonfoil",
//                      "foil"
//                   ],
//                   "oversized":false,
//                   "promo":false,
//                   "reprint":false,
//                   "variation":false,
//                   "set_id":"c1d109bc-ffd8-428f-8d7d-3f8d7e648046",
//                   "set":"tsp",
//                   "set_name":"Time Spiral",
//                   "set_type":"expansion",
//                   "set_uri":"https://api.scryfall.com/sets/c1d109bc-ffd8-428f-8d7d-3f8d7e648046",
//                   "set_search_uri":"https://api.scryfall.com/cards/search?order=set\\u0026q=e%3Atsp\\u0026unique=prints",
//                   "scryfall_set_uri":"https://scryfall.com/sets/tsp?utm_source=api",
//                   "rulings_uri":"https://api.scryfall.com/cards/0000579f-7b35-4ed3-b44c-db2a538066fe/rulings",
//                   "prints_search_uri":"https://api.scryfall.com/cards/search?order=released\\u0026q=oracleid%3A44623693-51d6-49ad-8cd7-140505caf02f\\u0026unique=prints",
//                   "collector_number":"157",
//                   "digital":false,
//                   "rarity":"uncommon",
//                   "flavor_text":"\\"A rift opened, and our arrows were abruptly stilled. To move was to push the world. But the sliver's claw still twitched, red wounds appeared in Thed's chest, and ribbons of blood hung in the air.\\"\\n—Adom Capashen, Benalish hero",
//                   "card_back_id":"0aeebaf5-8c7d-4636-9e82-8c27447861f7",
//                   "artist":"Paolo Parente",
//                   "artist_ids":[
//                      "d48dd097-720d-476a-8722-6a02854ae28b"
//                   ],
//                   "illustration_id":"2fcca987-364c-4738-a75b-099d8a26d614",
//                   "border_color":"black",
//                   "frame":"2003",
//                   "full_art":false,
//                   "textless":false,
//                   "booster":true,
//                   "story_spotlight":false,
//                   "edhrec_rank":5583,
//                   "penny_rank":10306,
//                   "prices":{
//                      "usd":"0.31",
//                      "usd_foil":"4.50",
//                      "usd_etched":null,
//                      "eur":"0.25",
//                      "eur_foil":"0.49",
//                      "tix":"0.02"
//                   },
//                   "related_uris":{
//                      "gatherer":"https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=109722",
//                      "tcgplayer_infinite_articles":"https://infinite.tcgplayer.com/search?contentMode=article\\u0026game=magic\\u0026partner=scryfall\\u0026q=Fury+Sliver\\u0026utm_campaign=affiliate\\u0026utm_medium=api\\u0026utm_source=scryfall",
//                      "tcgplayer_infinite_decks":"https://infinite.tcgplayer.com/search?contentMode=deck\\u0026game=magic\\u0026partner=scryfall\\u0026q=Fury+Sliver\\u0026utm_campaign=affiliate\\u0026utm_medium=api\\u0026utm_source=scryfall",
//                      "edhrec":"https://edhrec.com/route/?cc=Fury+Sliver"
//                   }
//                }""";
//
//        ScryfallCard card = scryfallService.getCardFromJSON( json );
//
//        assertNotNull( card );
//
//        assertEquals( "card",
//                      card.getObject() );
//        assertEquals( "0000579f-7b35-4ed3-b44c-db2a538066fe",
//                      card.getId() );
//        assertEquals( "44623693-51d6-49ad-8cd7-140505caf02f",
//                      card.getOracleId() );
//        assertEquals( 1,
//                      card.getMultiverseIds().size() );
//        assertEquals( 109722,
//                      card.getMultiverseIds().get( 0 ) );
//        assertEquals( 25527,
//                      card.getMtgoId() );
//        assertEquals( 25528,
//                      card.getMtgoFoilId() );
//        assertEquals( 14240,
//                      card.getTcgplayerId() );
//        assertEquals( 13850,
//                      card.getCardmarketId() );
//        assertEquals( "Fury Sliver",
//                      card.getName() );
//        assertEquals( "en",
//                      card.getLangauage() );
//        assertEquals( "2006-10-06",
//                      card.getReleasedAt() );
//        assertEquals( "https://api.scryfall.com/cards/0000579f-7b35-4ed3-b44c-db2a538066fe",
//                      card.getUri() );
//        assertEquals( "https://scryfall.com/card/tsp/157/fury-sliver?utm_source=api",
//                      card.getScryfallUri() );
//        assertEquals( "normal",
//                      card.getLayout() );
//        assertEquals( true,
//                      card.getHighresImage() );
//        assertEquals( "highres_scan",
//                      card.getImageStatus() );
//        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/small/front/0/0/0000579f-7b35-4ed3-b44c"
//                      + "-db2a538066fe.jpg?1562894979",
//                      card.getImageUris().getSmall() );
//        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/normal/front/0/0/0000579f-7b35-4ed3-b44c"
//                      + "-db2a538066fe.jpg?1562894979",
//                      card.getImageUris().getNormal() );
//        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/large/front/0/0/0000579f-7b35-4ed3-b44c"
//                      + "-db2a538066fe.jpg?1562894979",
//                      card.getImageUris().getLarge() );
//        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/png/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe"
//                      + ".png?1562894979",
//                      card.getImageUris().getPng() );
//        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/art_crop/front/0/0/0000579f-7b35-4ed3-b44c"
//                      + "-db2a538066fe.jpg?1562894979",
//                      card.getImageUris().getArtCrop() );
//        assertEquals( "https://c1.scryfall.com/file/scryfall-cards/border_crop/front/0/0/0000579f-7b35-4ed3-b44c"
//                      + "-db2a538066fe.jpg?1562894979",
//                      card.getImageUris().getBorderCrop() );
//        assertEquals( "{5}{R}",
//                      card.getManaCost() );
//        assertEquals( "6.0",
//                      card.getCmc() );
//        assertEquals( "Creature — Sliver",
//                      card.getTypeLine() );
//        assertEquals( "All Sliver creatures have double strike.",
//                      card.getOracleText() );
//        assertEquals( "3",
//                      card.getPower() );
//        assertEquals( "4",
//                      card.getToughness() );
//        assertEquals( 1,
//                      card.getColors().size() );
//        assertEquals( "R",
//                      card.getColors().get( 0 ) );
//        assertEquals( 1,
//                      card.getColorIdentity().size() );
//        assertEquals( "R",
//                      card.getColorIdentity().get( 0 ) );
//        assertEquals( 0,
//                      card.getKeywords().size() );
//        assertEquals( "en", card.getLangauage() );
//
//        assertEquals( "c1d109bc-ffd8-428f-8d7d-3f8d7e648046",
//                      card.getSetId() );
//                assertEquals( "tsp",
//                              card.getSet() );
//                assertEquals( "Time Spiral",
//                              card.getSetName() );
//                assertEquals( "https://api.scryfall.com/sets/c1d109bc-ffd8-428f-8d7d-3f8d7e648046",
//                              card.getSetUri() );
//        assertEquals( "uncommon",
//                      card.getRarity() );
//        assertEquals( "157",
//                      card.getCollectorNumber() );
//        assertEquals( .31,
//                      card.getPrices().getUsd() );
//        assertEquals( 4.50,
//                      card.getPrices().getUsdFoil() );
//        assertEquals( null,
//                      card.getPrices().getUsdEtched() );
//        assertEquals( .25,
//                      card.getPrices().getEur() );
//        assertEquals( .49,
//                      card.getPrices().getEurFoil() );
//        assertEquals( .02,
//                      card.getPrices().getTix() );
//            }
        }


