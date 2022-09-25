package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.entity.Deck;
import com.rezatron.mtgprice.entity.User;
import com.rezatron.mtgprice.service.DeckService;
import com.rezatron.mtgprice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/v1/decks" )
@Slf4j
public
class DeckController {

    @Autowired
    DeckService deckService;
    @Autowired
    UserService userService;

    @PostMapping( "/user/{userId}/deckName/{deckName}" )
    public
    ResponseEntity addDeck(
            @PathVariable( "userId" )
            String userId,
            @PathVariable( "deckName" )
            String deckName,
            @RequestBody
            String deckList)
    {
        User user = userService.findById( userId );
        if (user == null) {
            log.warn( "No user found with id {}",
                      userId );
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "No user with userid " + userId + "." );
        }
        Deck deck = deckService.createDeck( deckList,
                                            deckName,
                                            user );
        return ResponseEntity.status( HttpStatus.CREATED ).body( deck );
    }
}
