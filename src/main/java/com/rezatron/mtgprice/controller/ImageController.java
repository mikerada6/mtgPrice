package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping( "/api/v1/images" )
@Slf4j
public
class ImageController {
    @Autowired
    ImageService imageService;

    @Value( "${mtg.download.baselocation}" )
    private String baseFileLocation;

    @GetMapping( "/" )
    public
    ResponseEntity basicSetup()
    {
        ArrayList<String> folders = new ArrayList<>( Arrays.asList( "set",
                                                                    "color",
                                                                    "type",
                                                                    "rarity",
                                                                    "all" ) );
        for (String folder : folders) {
            String path = "/Volumes/Magic/images/" + folder;
            path = baseFileLocation + "/images/" + folder;

            File directory = new File( path );
            if (!directory.exists()) {
                directory.mkdirs();
                log.info( "Creating folder {}.",
                          path );
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }
        }
//        imageService.getImages();
        imageService.saveAllImages();
        return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( "Created image folder" );
    }
}
