package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.service.DeckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/v1/decks" )
@Slf4j
public
class DeckController {

    @Autowired
    DeckService deckService;

}
