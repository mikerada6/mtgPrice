package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping( "/api/v1/images" )
@Slf4j
public
class ImageController {
    @Autowired
    FileService fileService;

    @Value( "${mtg.download.baselocation}" )
    private String baseFileLocation;

    @PostMapping( "/" )
    public
    ResponseEntity basicSetup()
    {
        ArrayList<String> folders = new ArrayList<>( Arrays.asList( "set",
                                                                    "color",
                                                                    "type",
                                                                    "rarity",
                                                                    "all" ) );
        for (String folder : folders) {
            String path = baseFileLocation + "/images/" + folder;

            if (!fileService.createFolder( path )) {
                return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error creating folders." );
            }
        }
//        imageService.saveAllImages();
        return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( "Created image folder." );
    }
}
