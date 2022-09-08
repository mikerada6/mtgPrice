package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import com.rezatron.mtgprice.service.CardService;
import com.rezatron.mtgprice.service.FileService;
import com.rezatron.mtgprice.service.ScryfallService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/scryfall" )
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public
class ScryfallController {
    @Autowired
    ScryfallService scryfallService;
    @Autowired
    FileService fileService;
    @Autowired
    CardService cardService;




    @PostMapping( "/api/v1/bulkDataUpdate" )
    public
    ResponseEntity bulkDataUpdate(
            @RequestParam( name = "fileLocation",
                           required = false )
            String fileLocation)
    {
        log.info( "bulkDataUpdate" );
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        if (fileLocation == null) {
            log.info( "no file location was given." );
            fileLocation = scryfallService.downloadData();
            log.info( "File is now \t {}.",
                      fileLocation );

        }
        String file = null;
        try {
            file = fileService.loadFile( fileLocation );
        } catch (IOException e) {
            log.error( "Unable to read file {}.",
                       fileLocation );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Unable to read" );
        }
        List<ScryfallCard> cards = scryfallService.convertDTO( file );


        long updatedCardCount = scryfallService.saveCards( cards,
                                                           fileLocation );
        stopWatch.stop();
        log.info("Bulk update is now completed {} records added in {}.", updatedCardCount, stopWatch.toString());
        return ResponseEntity.status( HttpStatus.CREATED).body( updatedCardCount );
    }
}
