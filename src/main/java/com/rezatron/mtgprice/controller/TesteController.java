package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.queue.QueueSender;
import com.rezatron.mtgprice.service.CardService;
import com.rezatron.mtgprice.service.ScryfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping( "/teste" )
public
class TesteController {

    @Autowired
    ScryfallService scryfallService;
    @Autowired
    CardService cardService;
    @Autowired
    private QueueSender queueSender;

    @GetMapping
    public
    String send() {
        queueSender.send( "test message" );
        return "ok. done";
    }

    @PostMapping( "/mongo" )
    public
    String testingMongo() {
        List<String> ids = new ArrayList<>( Arrays.asList( "dc4e2134-f0c2-49aa-9ea3-ebf83af1445c",
                                                           "badid",
                                                           "3a21a6ae-b2f2-4f0c-acfd-5f3e8d63fd2f" ) );
        List<String> missingIds = cardService.findIdsNotInDatabase( ids );
        queueSender.send( "test message" );
        return "ok. done";
    }


}
