package com.rezatron.mtgprice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping( "/api/v1" )
@Slf4j
public
class AliveController {

    @SuppressWarnings( "SpringJavaInjectionPointsAutowiringInspection" )
    @Autowired
    BuildProperties buildProperties;
    @Value( "${mtg.download.baselocation}" )
    private String baseFileLocation;

    @GetMapping( "/alive" )
    public
    ResponseEntity<String> alive() {
        log.info( "Alive" );
        return ResponseEntity.status( HttpStatus.OK ).body( "alive" );
    }

    @GetMapping( "/version" )
    public
    ResponseEntity<HashMap<String, String>> version() {
        log.info( "Version" );
        HashMap<String, String> info = new HashMap<>();
        info.put( "version",
                  buildProperties.getVersion() );
        info.put( "time",
                  buildProperties.getTime().toString() );
        info.put( "baseFileLocation",
                  baseFileLocation );

        log.info( "version - {}.",
                  info.get( "version" ) );
        log.info( "time - {}.",
                  info.get( "time" ) );
        return ResponseEntity.status( HttpStatus.OK ).body( info );
    }
}
