package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.queue.QueueSender;
import com.rezatron.mtgprice.service.CardService;
import com.rezatron.mtgprice.service.ScryfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
