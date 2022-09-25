package com.rezatron.mtgprice.controller;

import com.google.gson.JsonSyntaxException;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import com.rezatron.mtgprice.service.CardService;
import com.rezatron.mtgprice.service.FileService;
import com.rezatron.mtgprice.service.ScryfallService;
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
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/scryfall" )
@Slf4j
public
class ScryfallController {
    @Autowired
    ScryfallService scryfallService;
    @Autowired
    FileService fileService;
    @Autowired
    CardService cardService;


    @PostMapping( "/api/v1/reloadFromBackUp" )
    public
    ResponseEntity reloadFromBackUp()
    {
        HashMap<String, String> response = new HashMap<>();
        log.info( "bulkDataUpdate" );
        List<String> files = fileService.getAvailableBulkDataFiles();
        log.info( "{} files to review",
                  files.size() );
        for (String fileLocation : files) {
            ResponseEntity tempResponse = bulkDataUpdate( fileLocation );

            response.put( fileLocation,
                          tempResponse.getStatusCode().toString() );

        }
        return ResponseEntity.status( HttpStatus.OK ).body( response );
    }

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
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Unable to read." );
        }
        try {
            List<ScryfallCard> cards = scryfallService.convertDTO( file );

            scryfallService.sendMessages( cards,
                                          fileLocation );

            stopWatch.stop();
            log.info( "Bulk update is now completed {} records added to the queue in {}.",
                      cards.size(),
                      stopWatch );
            return ResponseEntity.status( HttpStatus.ACCEPTED ).body( cards.size() );
        } catch (JsonSyntaxException e) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                                 .body( "Ran into a problem trying to convert the ScryFall cards." );
        }
    }
}
