package com.rezatron.mtgprice.queue;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import com.rezatron.mtgprice.service.ScryfallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public
class QueueConsumer {

    public static Gson gson = new Gson();
    @Autowired
    ScryfallService scryfallService;

    @RabbitListener( queues = {"${queue.name}"} )
    public
    void receive(
            @Payload
            String fileBody)
    {
        log.info( "Received message." );
        try {
            List<ScryfallCard> cardArray = Arrays.asList( gson.fromJson( fileBody,
                                                                         ScryfallCard[].class ) );
            scryfallService.saveCards( cardArray );
        } catch (JsonSyntaxException e) {
            log.error( "Could not convert the cards" );
            List<ScryfallCard> cardArray = Arrays.asList( gson.fromJson( fileBody,
                                                                         ScryfallCard[].class ) );
            scryfallService.saveCards( cardArray );
        } catch (Exception e) {
            log.error( "what is the problem here?" );
        }


    }


}
