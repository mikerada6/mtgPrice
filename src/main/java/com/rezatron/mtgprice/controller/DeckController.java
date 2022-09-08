package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.dto.magic.Deck;
import com.rezatron.mtgprice.service.CardService;
import com.rezatron.mtgprice.service.DeckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/decks" )
@Slf4j
public
class DeckController {

    @Autowired
    DeckService deckService;

    @PostMapping( "/" )
    public
    ResponseEntity getSuperTypes(String deckList)
    {
        Deck deck = deckService.createDeck( deckList);
        return ResponseEntity.status( HttpStatus.CREATED ).body( deck );
    }
}
