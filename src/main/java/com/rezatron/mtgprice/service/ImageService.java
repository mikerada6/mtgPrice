package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.dto.magic.Card;
import com.rezatron.mtgprice.dto.magic.CardFace;
import com.rezatron.mtgprice.dto.magic.CardFaceImages;
import com.rezatron.mtgprice.dto.magic.Images;
import com.rezatron.mtgprice.repository.CardFaceRepository;
import com.rezatron.mtgprice.repository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public
class ImageService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardFaceRepository cardFaceRepository;

    @Value( "${mtg.download.baselocation}" )
    private String baseFileLocation;

    public
    void saveAllImages() {
        List<String> sets = cardRepository.findDistinctSets();
        log.info( "Found {} different sets.",
                  sets.size() );
        for (String set : sets) {
            HashMap<String, URL> imagesToDownload = new HashMap<>();
            log.info( "working on set: {}.",
                      set );
            List<Card> cards = cardRepository.findByMtgSetOrderByCollectorNumberAsc( set );
            List<CardFace> cardFaces = cardFaceRepository.findByCard_MtgSetOrderByCard_CollectorNumberAsc( set );
            for (Card c : cards) {
                URL url = getImageLocations( c );
                if (url != null) {
                    String path = baseFileLocation + "/images/all/" + c.getId() + ".jpeg";
                    imagesToDownload.put( path,
                                          url );
                }
            }
            for (CardFace c : cardFaces) {
                URL url = getImageLocations( c );
                if (url != null) {
                    String path = baseFileLocation + "/images/all/" + c.getId() + ".jpeg";
                    imagesToDownload.put( path,
                                          url );
                }
            }

            log.info( "about to save {} cards from set {}.",
                      imagesToDownload.size(),
                      set );
            for (String key : imagesToDownload.keySet()) {
                if (!Files.exists( Path.of( key ) )) {
                    try {
                        InputStream in = new BufferedInputStream( imagesToDownload.get( key ).openStream() );
                        OutputStream out = new BufferedOutputStream( new FileOutputStream( key ) );

                        for (int i; (i = in.read()) != -1; ) {
                            out.write( i );
                        }
                        in.close();
                        out.close();
                    } catch (Exception e) {
                        log.error( "Error trying to save at {} with error {}.",
                                   key,
                                   e );
                    }
                    //We introduce a slight delay so we don't overhit scryfall's severs
                    try {
                        Thread.sleep( 100 );
                    } catch (InterruptedException e) {
                        throw new RuntimeException( e );
                    }
                } else {
                    log.info( "Skipping {} because it already exists.",
                              key );
                }
            }
        }
    }

    private
    URL getImageLocations(Card c)
    {
        if (c.getImages() == null) {
            return null;
        }
        Images images = c.getImages();
        String urlString = null;
        if (images.getLarge() != null) {
            urlString = images.getLarge();
        } else if (images.getNormal() != null) {
            urlString = images.getNormal();
        } else if (images.getSmall() != null) {
            urlString = images.getSmall();
        }
        try {
            if (urlString != null) {
                return new URL( urlString );
            }
        } catch (MalformedURLException e) {
            log.error( "Error getting image url on card {} with error {}.",
                       c.getId(),
                       e );
        }
        return null;
    }

    private
    URL getImageLocations(CardFace c)
    {
        if (c.getImages() == null) {
            return null;
        }
        CardFaceImages images = c.getImages();
        String urlString = null;
        if (images.getLarge() != null) {
            urlString = images.getLarge();
        } else if (images.getNormal() != null) {
            urlString = images.getNormal();
        } else if (images.getSmall() != null) {
            urlString = images.getSmall();
        }
        try {
            if (urlString != null) {
                return new URL( urlString );
            }
        } catch (MalformedURLException e) {
            log.error( "Error getting image url on cardFace {} with error {}.",
                       c.getId(),
                       e );
        }
        return null;
    }

}
