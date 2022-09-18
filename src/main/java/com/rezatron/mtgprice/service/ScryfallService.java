package com.rezatron.mtgprice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.rezatron.mtgprice.dto.magic.scryfall.BulkData;
import com.rezatron.mtgprice.dto.magic.scryfall.BulkDataInformation;
import com.rezatron.mtgprice.dto.magic.scryfall.Datum;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import com.rezatron.mtgprice.entity.Price;
import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.exception.ScryFallException;
import com.rezatron.mtgprice.queue.QueueSender;
import com.rezatron.mtgprice.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public
class ScryfallService {

    private final DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH-mm-ss" );
    private final DateTimeFormatter cardDateFormat = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss" );


    @Autowired
    CardService cardService;
    @Autowired
    QueueSender queueSender;
    @Value( "${mtg.download.baselocation}" )
    private String baseFileLocation;
    @Value( "${mtg.bulkData.type}" )
    private String bulkDataType;
    @Value( "#{new Integer('${mtg.bulkData.batchsize}')}" )
    private Integer batchSize;
    @Autowired
    private FileService fileService;
    @Autowired
    private
    PriceRepository priceRepository;

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
    List<ScryfallCard> convertDTO(String file) throws JsonSyntaxException {
        Gson gson = new Gson();
        try {
            log.info( "Converting data to ScryfallCard." );
            List<ScryfallCard> cardArray = Arrays.asList( gson.fromJson( file,
                                                                         ScryfallCard[].class ) );
            log.info( "Converting data to ScryfallCard completed." );
            return cardArray;
        } catch (JsonSyntaxException e) {
            log.error( "Unable to convert cardArray {}.",
                       e );
            throw e;
        }

    }

    @Transactional
    public
    long saveCards(List<ScryfallCard> scryfallCards) {

        log.info( "Saving {} cards.",
                  scryfallCards.size() );
        List<Card> cardsToSave = new ArrayList<>();
        long savedCards = 0;
        long skippedCards = 0;
        for (ScryfallCard scryfallCard : scryfallCards) {
            String dateTime = scryfallCard.getTimeStamp();
            LocalDateTime timeStamp = LocalDateTime.parse( dateTime,
                                                           cardDateFormat );
            Card tempCard = cardService.updateCard( scryfallCard,
                                                    Optional.of( timeStamp ) );
            if (tempCard != null) {
                cardsToSave.add( tempCard );
                if (cardsToSave.size() >= batchSize) {
                    log.info( "Saving {} cards.",
                              cardsToSave.size() );
                    savedCards += cardService.saveAll( cardsToSave ).size();
                    cardsToSave.clear();
                    log.info( "Done saving batch.  Still have {} left to save.",
                              scryfallCards.size() - savedCards - skippedCards );
                }
            } else {
                skippedCards++;
            }
        }
        log.info( "Saving {} cards.",
                  cardsToSave.size() );
        savedCards += cardService.saveAll( cardsToSave ).size();
        return savedCards;
    }


    public
    Card saveCard(ScryfallCard scryfallCard) {

        String dateTime = scryfallCard.getTimeStamp();
        LocalDateTime timeStamp = LocalDateTime.parse( dateTime,
                                                       cardDateFormat );

        Card tempCard = cardService.updateCard( scryfallCard,
                                                Optional.of( timeStamp ) );
        return cardService.save( tempCard );
    }

    @Transactional
    public
    void sendMessages(List<ScryfallCard> cards, String fileLocation) {
        ObjectMapper mapper = new ObjectMapper();
        String[] longFile = fileLocation.split( "/" );
        String dateTime = longFile[longFile.length - 1].replace( ".json",
                                                                 "" );
        LocalDateTime timeStamp = LocalDateTime.parse( dateTime,
                                                       fileDateFormat );
        ArrayList<ScryfallCard> cardsToSend = new ArrayList<>();
        cards = cards.stream()
                     .filter( scryfallCard -> scryfallCard.getGames().contains( "paper" ) && scryfallCard.getLangauage()
                                                                                                         .equalsIgnoreCase( "en" ) )
                     .collect( Collectors.toList() );
        int cardsSize = cards.size();
        log.info("Getting prices already in database");
        List<String> alreadyInDataBase = priceRepository.findCardIdsByTimestamp( timeStamp );
        log.info("{} prices in database looking to add {} new cards.", alreadyInDataBase.size(), cards.size());
        cards = cards.stream().filter(c -> !alreadyInDataBase.contains( c.getId() )).collect( Collectors.toList());
        log.info("{} was reduced down to {}", cardsSize, cards.size());

        for (ScryfallCard card : cards) {
            card.setTimeStamp( timeStamp.toString() );

            cardsToSend.add( card );
            if (cardsToSend.size() >= batchSize) {
                try {
                    String jsonInString = mapper.writeValueAsString( cardsToSend );
                    log.info( "Sending off a batch of {} cards.",
                              cardsToSend.size() );
                    queueSender.send( jsonInString );
                    cardsToSend.clear();
                } catch (JsonProcessingException e) {
                    log.error( "Could not convert and send as a json." );
                }
            }


        }
        if (cardsToSend.size() >= 0) {
            try {
                String jsonInString = mapper.writeValueAsString( cardsToSend );
                log.info( "Sending off a batch of {} cards.",
                          cardsToSend.size() );
                queueSender.send( jsonInString );
                cardsToSend.clear();
            } catch (JsonProcessingException e) {
                log.error( "Could not convert and send as a json." );
            }
        }

    }
}
