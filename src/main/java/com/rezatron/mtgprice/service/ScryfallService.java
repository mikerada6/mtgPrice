package com.rezatron.mtgprice.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rezatron.mtgprice.exception.ScryFallException;
import com.rezatron.mtgprice.dto.magic.Card;
import com.rezatron.mtgprice.dto.magic.scryfall.BulkData;
import com.rezatron.mtgprice.dto.magic.scryfall.BulkDataInformation;
import com.rezatron.mtgprice.dto.magic.scryfall.Datum;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public
class ScryfallService {

    private final DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH-mm-ss" );

    @Autowired
    CardService cardService;

    @Value( "${mtg.download.baselocation}" )
    private String baseFileLocation;
    @Value( "${mtg.bulkData.type}" )
    private String bulkDataType;

    @Value("#{new Integer('${mtg.bulkData.batchsize}')}")
    private Integer batchSize;
    @Autowired
    private FileService fileService;

    public
    ScryfallCard getCardFromJSON(String json)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ScryfallCard card = gson.fromJson( json,
                                           ScryfallCard.class );
        return card;
    }

    public
    String makeHTTPGetRequest(String url) throws IOException
    {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url( url ).method( "GET",
                                                                   null ).build();
        Gson gson = new Gson();
        ResponseBody responseBody = null;
        try {
            responseBody = client.newCall( request ).execute().body();
        } catch (IOException e) {
            log.error( "IOException {} during get request.",
                       e );
            throw e;
        }
        return responseBody.string();
    }

    public
    String downloadData() throws ScryFallException {
        //get the latest data from scryfall
        BulkDataInformation bdi = null;
        try {
            bdi = downloadLatestBulkDataFromScryfall();
        } catch (ScryFallException e) {
            log.error( "Error: {}",
                       e );
            throw e;
        }

        //check to see if the file already exists

        String file = baseFileLocation + "/bulkData/" + bdi.getTimestamp().format( fileDateFormat ) + ".json";
        boolean fileExists = fileService.doesFileExist( file );
        if (fileExists) {
            log.info( "" + "No need to get fresh bulk data." );
            return null;
        }

        //download the actual data
        String data = null;
        try {
            data = makeHTTPGetRequest( bdi.getUrl() );
        } catch (IOException e) {
            log.error( "Could not get the newest bulk data" );
            throw new ScryFallException( "Could not get the newest bulk data" );
        }

        try {
            fileService.saveFile( file,
                                  data );
        } catch (IOException e) {
            log.error( "Could not save bulk data." );
            throw new ScryFallException( "Could not save bulk data." );
        }
        return file;
    }

    public
    BulkDataInformation downloadLatestBulkDataFromScryfall() throws ScryFallException {
        log.info( "downloading the latest bulk data from scryfall." );

        //go to scryfall and find the endpoint for the latest bulk data
        String bulkDataResult = null;
        try {
            bulkDataResult = makeHTTPGetRequest( "https://api.scryfall.com/bulk-data" );
        } catch (IOException e) {
            log.error( "Could not get the bulk data info from scryfall" );
            throw new ScryFallException( "Could not get the bulk data info from scryfall" );
        }
        Gson gson = new Gson();
        BulkData bulkData = null;
        bulkData = gson.fromJson( bulkDataResult,
                                  BulkData.class );

        Datum bulkDataDetailed = bulkData.getData().stream().filter( d -> d.getName().equalsIgnoreCase( bulkDataType ) )
                                         .findFirst().orElse( null );
        if (bulkDataDetailed == null) {
            log.error( "Could not find bulk data {} from {}.",
                       bulkDataType,
                       bulkData );
            throw new ScryFallException( "\"Could not find bulk data " + bulkDataType );
        }
        BulkDataInformation bdi = new BulkDataInformation();
        bdi.setUrl( bulkDataDetailed.getDownloadUri() );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSXXXXX" );
        LocalDateTime timestamp = LocalDateTime.parse( bulkDataDetailed.getUpdatedAt(),
                                                       formatter );
        bdi.setTimestamp( timestamp );
        return bdi;
    }


    public
    List<ScryfallCard> convertDTO(String file) {
        Gson gson = new Gson();
        try {
            log.info( "Converting data to ScryfallCard." );
            List<ScryfallCard> cardArray = Arrays.asList( gson.fromJson( file,
                                                                         ScryfallCard[].class ) );
            log.info( "Converting data to ScryfallCard completed." );
            return cardArray;
        } catch (Exception e) {
            log.error( "Unable to convert cardArray {}.",
                       e );
        }
        return null;
    }

    public
    long saveCards(List<ScryfallCard> scryfallCards, String fileLocation) {

        String[] longFile = fileLocation.split( "/" );
        String dateTime = longFile[longFile.length - 1].replace( ".json","" );
        LocalDateTime timeStamp = LocalDateTime.parse(dateTime, fileDateFormat);;
        log.info( "Saving {} cards for {}.",
                   scryfallCards.size(),
                   dateTime );
        List<Card> cardsToSave = new ArrayList<>();
        long savedCards = 0;
        long skippedCards = 0;
        for (ScryfallCard scryfallCard : scryfallCards) {
            Card tempCard = cardService.updateCard( scryfallCard,
                                                    Optional.of(timeStamp) );
            if (tempCard != null) {
                cardsToSave.add( tempCard );
                if (cardsToSave.size() >= batchSize) {
                    log.info( "Saving {} cards.",
                              cardsToSave.size() );
                    savedCards += cardService.saveAll( cardsToSave ).size();
                    cardsToSave.clear();
                    log.info( "Done saving batch.  Still have {} left to save.",
                              scryfallCards.size() - savedCards - skippedCards);
                }
            }
            else {
                skippedCards++;
            }
        }
        log.info( "Saving {} cards.",
                  cardsToSave.size() );
        savedCards += cardService.saveAll( cardsToSave ).size();
        return savedCards;
    }
}
