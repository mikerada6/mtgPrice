package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/v1/cards" )
@Slf4j
public
class CardController {

    @Autowired
    CardService cardService;

    @GetMapping( "cardId/{cardId}" )
    public
    ResponseEntity getCard(
            @PathVariable( "cardId" )
            String cardId)
    {
        Card card = cardService.findById( cardId );
        if (card == null) {
            return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( "Could not find a card with id of " + cardId );
        }
        return ResponseEntity.status( HttpStatus.OK ).body( card );
    }

    @GetMapping( "count" )
    public
    ResponseEntity getCard()
    {
        long cardCount = cardService.count( );

        return ResponseEntity.status( HttpStatus.OK ).body( cardCount );
    }


}
