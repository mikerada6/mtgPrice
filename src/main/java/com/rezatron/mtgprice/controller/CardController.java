package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/cards" )
@Slf4j
public
class CardController {

    @Autowired
    CardService cardService;

    @GetMapping( "/superTypes" )
    public
    ResponseEntity getSuperTypes()
    {
        List<String> superTypes = cardService.getSuperTypes();
        return ResponseEntity.status( HttpStatus.OK ).body( superTypes );
    }
}
