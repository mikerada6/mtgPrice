package com.rezatron.mtgprice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.rezatron.mtgprice.dto.LegalStatus;
import com.rezatron.mtgprice.dto.PriceUpdate;
import com.rezatron.mtgprice.dto.magic.scryfall.BulkData;
import com.rezatron.mtgprice.dto.magic.scryfall.BulkDataInformation;
import com.rezatron.mtgprice.dto.magic.scryfall.Datum;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.dto.magic.wizards.Rarity;
import com.rezatron.mtgprice.entity.Legalities;
import com.rezatron.mtgprice.entity.Price;
import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.entity.wizards.Printing;
import com.rezatron.mtgprice.exception.ScryFallException;
import com.rezatron.mtgprice.queue.QueueSender;
import com.rezatron.mtgprice.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public
class ScryfallService {

    private final DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH-mm-ss" );
    private final DateTimeFormatter cardDateFormat = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss" );
    private final DateTimeFormatter releaseDate = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );


    @Autowired
    CardService cardService;
    @Autowired
    PriceService priceService;
    @Autowired
    QueueSender queueSender;
    @Autowired
    CardRepository cardRepository;
    @Value( "${mtg.download.baselocation}" )
    private String baseFileLocation;
    @Value( "${mtg.bulkData.type}" )
    private String bulkDataType;
    @Value( "#{new Integer('${mtg.bulkData.batchsize}')}" )
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
        log.info( "Savings {} cards.",
                  scryfallCards.size() );
        Map<String, ScryfallCard> cardMap = scryfallCards.stream().collect( Collectors.toMap( ScryfallCard::getOracleId,
                                                                                              Function.identity(),
                                                                                              (card1, card2) -> {
                                                                                                  return card1;
                                                                                              } ) );
        long savedCards = 0L;
        long savedPrintings = 0L;
        long savedPrices = 0L;
        log.info( "Starting to save missing cards." );
        List<Card> cardsToSave = new ArrayList<>();
        List<String> cardIds = scryfallCards.stream().map( c -> c.getOracleId() ).collect( Collectors.toList() );
        List<String> cardIdsMissing = cardRepository.findIdsNotInDatabase( cardIds );
        if (cardIdsMissing.size() > 0) {
            log.info( "{} cards are missing from the database Savings those first.",
                      cardIdsMissing.size() );
            for (String id : cardIdsMissing) {
                Card card = createCardFromScryfallCard( cardMap.get( id ) );
                if (card.getId() == null) {
                    if (cardMap.get( id ).getCardFaces().size() > 0) {
                        card.setId( cardMap.get( id ).getCardFaces().get( 0 ).getOracleId() );
                    } else {
                        log.error( "Could not come up with an id for a card." );
                    }
                }
                cardsToSave.add( card );
                if (cardsToSave.size() >= batchSize) {
                    log.info( "savings {} cards.",
                              cardsToSave.size() );
                    cardRepository.saveAll( cardsToSave );
                    savedCards += cardsToSave.size();

                    cardsToSave.clear();
                }
            }
            if (cardsToSave.size() > 0) {
                log.info( "savings {} cards.",
                          cardsToSave.size() );
                cardRepository.saveAll( cardsToSave );
                savedCards += cardsToSave.size();

                cardsToSave.clear();
            }
        } else {
            log.info( "No new cards to save." );
        }

        log.info( "Starting to save missing printings." );
        List<String> printingIds = scryfallCards.stream().map( c -> c.getPrintingId() ).collect( Collectors.toList() );
        List<String> printingIdsMissing = cardRepository.findPrintingIdsNotInDatabase( printingIds );
        if (printingIdsMissing.size() > 0) {
            log.info( "{} printings are missing from the database Savings those first.",
                      printingIdsMissing.size() );
            for (String id : printingIdsMissing) {
                String cardId = id.split( "_" )[0];
                String printingId = id.split( "_" )[1];
                ScryfallCard tempCard = cardMap.get( cardId );
                Card card = cardRepository.findById( cardId ).get();
                card.addPrinting( createPrintingFromScryfallCard( tempCard ) );
                cardRepository.save( card );
                savedPrintings++;
            }
        } else {
            log.info( "No new printings to save." );
        }


        List<PriceUpdate> priceUpdatesToSave = new ArrayList<>();
        log.info( "Starting to save prices." );
        LocalDateTime.now( ZoneOffset.UTC );
        for (ScryfallCard scryfallCard : scryfallCards) {
            String dateTime = scryfallCard.getTimeStamp();
            DateTime timeStamp = ISODateTimeFormat.dateTimeParser().parseDateTime( dateTime + 'Z' );
            String cardId = scryfallCard.getOracleId();
            String printingId = cardId + "_" + scryfallCard.getId();
            String priceId = printingId + "_" + timeStamp;
            Price price = Price.builder().id( priceId ).usd( scryfallCard.getPrices().getUsd() )
                               .usdFoil( scryfallCard.getPrices().getUsdFoil() )
                               .usdEtched( scryfallCard.getPrices().getUsdEtched() )
                               .eur( scryfallCard.getPrices().getEur() )
                               .eurFoil( scryfallCard.getPrices().getEurFoil() )
                               .tix( scryfallCard.getPrices().getTix() ).timestamp( timeStamp ).build();
            if (price.worthSaving()) {
                priceUpdatesToSave.add( PriceUpdate.builder().cardId( cardId ).printingId( printingId ).price( price )
                                                   .build() );
                if (priceUpdatesToSave.size() >= batchSize) {
                    log.info( "savings {} prices.",
                              priceUpdatesToSave.size() );
                    boolean saved = cardRepository.addPricesToCard( priceUpdatesToSave );
                    if (saved) {
                        savedPrices += priceUpdatesToSave.size();
                    } else {
                        log.error( "We had an error saving {} prices.",
                                   priceUpdatesToSave.size() );
                    }
                    priceUpdatesToSave.clear();
                }
            }
        }
        if (priceUpdatesToSave.size() > 0) {
            log.info( "savings final {} prices.",
                      priceUpdatesToSave.size() );
            cardRepository.addPricesToCard( priceUpdatesToSave );
            savedPrices += priceUpdatesToSave.size();
            priceUpdatesToSave.clear();
        }
        log.info( "Done savings {} cards {} printings {} prices.",
                  savedCards,
                  savedPrintings,
                  savedPrices );
        return savedCards;
    }

//    @Transactional
//    public
//    long saveCards(List<ScryfallCard> scryfallCards) {
//
//        log.info( "Saving {} cards.",
//                  scryfallCards.size() );
//
//        Map<String, Card> dataBaseCards = cardService
//                .findByIdIn( scryfallCards.stream().map( c -> c.getId() ).collect( Collectors.toList() ) ).stream()
//                .collect( Collectors.toMap( Card::getId,
//                                            Function.identity() ) );
//        List<Card> cardsToSave = new ArrayList<>();
//        List<Price> pricesToSave = new ArrayList<>();
//        long savedCards = 0;
//        long savedPrices = 0;
//        for (ScryfallCard scryfallCard : scryfallCards) {
//            String dateTime = scryfallCard.getTimeStamp();
//            LocalDateTime timeStamp = LocalDateTime.parse( dateTime,
//                                                           cardDateFormat );
//            if (dataBaseCards.containsKey( scryfallCard.getId() )) {
//                Price p = Price.builder().usd( scryfallCard.getPrices().getUsd() )
//                               .usdFoil( scryfallCard.getPrices().getUsdFoil() )
//                               .usdEtched( scryfallCard.getPrices().getUsdEtched() )
//                               .eur( scryfallCard.getPrices().getEur() )
//                               .eurFoil( scryfallCard.getPrices().getEurFoil() )
//                               .tix( scryfallCard.getPrices().getTix() ).timestamp( timeStamp )
//                               .card( dataBaseCards.get( scryfallCard.getId() ) ).build();
//                if (dataBaseCards.get( scryfallCard.getId() ).getUpdateDateTime().isBefore( timeStamp )) {
//                    Card tempCard = cardService.updateCard( scryfallCard,
//                                                            Optional.of( timeStamp ),
//                                                            Optional.of( dataBaseCards.get( scryfallCard.getId() ) ) );
//                    cardsToSave.add( tempCard );
//                }
//                pricesToSave.add( p );
//            } else {
//                Card tempCard = cardService.updateCard( scryfallCard,
//                                                        Optional.of( timeStamp ),
//                                                        Optional.ofNullable(null)  );
//                Price p = Price.builder().usd( scryfallCard.getPrices().getUsd() )
//                               .usdFoil( scryfallCard.getPrices().getUsdFoil() )
//                               .usdEtched( scryfallCard.getPrices().getUsdEtched() )
//                               .eur( scryfallCard.getPrices().getEur() )
//                               .eurFoil( scryfallCard.getPrices().getEurFoil() )
//                               .tix( scryfallCard.getPrices().getTix() ).timestamp( timeStamp ).card( tempCard )
//                               .build();
//                cardsToSave.add( tempCard );
//                pricesToSave.add( p );
//            }
//
//
//            if (pricesToSave.size() >= batchSize) {
//                log.info( "Saving {} prices and {} cards.",
//                          pricesToSave.size(),
//                          cardsToSave.size() );
//                savedCards += cardService.saveAll( cardsToSave ).size();
//                savedPrices += priceService.saveAll( pricesToSave ).size();
//                cardsToSave.clear();
//                pricesToSave.clear();
//                log.info( "Done saving batch.  Still have {} left to save.",
//                          scryfallCards.size() - savedPrices );
//
//            }
//        }
//        savedCards += cardService.saveAll( cardsToSave ).size();
//        savedPrices += priceService.saveAll( pricesToSave ).size();
//        log.info( "Saved a total of {} prices and {} cards.",
//                  savedPrices,
//                  savedCards );
//        return savedPrices;
//
//    }

    private
    Printing createPrintingFromScryfallCard(ScryfallCard scryfallCard)
    {
        return Printing.builder().id( scryfallCard.getPrintingId() ).mtgSet( scryfallCard.getSet() )
                       .mtgSetName( scryfallCard.getSetName() )
                       .releasedAt( LocalDate.from( LocalDate.parse( scryfallCard.getReleasedAt(),
                                                                     releaseDate ) ) )
                       .collectorNumber( scryfallCard.getCollectorNumber() ).scryfallId( scryfallCard.getId() )
                       .rarity( Rarity.fromShortName( scryfallCard.getRarity() ) ).build();
    }

    private
    Card createCardFromScryfallCard(ScryfallCard scryfallCard)
    {
        Card card = Card.builder().id( scryfallCard.getOracleId() ).name( scryfallCard.getName() )
                        .oracleText( scryfallCard.getOracleText() ).typeLine( scryfallCard.getTypeLine() )
                        .cmc( scryfallCard.getCmc() ).build();
        if (scryfallCard.getColorIdentity() != null) {
            card.setColorIdentity( scryfallCard.getColorIdentity().stream().map( c -> Color.getFromLabel( c ) )
                                               .collect( Collectors.toSet() ) );
        }
        if (scryfallCard.getColors() != null) {
            card.setColors( scryfallCard.getColors().stream().map( c -> Color.getFromLabel( c ) )
                                        .collect( Collectors.toSet() ) );
        }
        com.rezatron.mtgprice.dto.magic.scryfall.Legalities tempLegalities = scryfallCard.getLegalities();
        Legalities newLegalities = Legalities.builder().brawl( LegalStatus.getFromLabel( tempLegalities.getBrawl() ) )
                                             .alchemy( LegalStatus.getFromLabel( tempLegalities.getAlchemy() ) )
                                             .commander( LegalStatus.getFromLabel( tempLegalities.getCommander() ) )
                                             .duel( LegalStatus.getFromLabel( tempLegalities.getDuel() ) )
                                             .explorer( LegalStatus.getFromLabel( tempLegalities.getExplorer() ) )
                                             .future( LegalStatus.getFromLabel( tempLegalities.getFuture() ) )
                                             .gladiator( LegalStatus.getFromLabel( tempLegalities.getGladiator() ) )
                                             .historic( LegalStatus.getFromLabel( tempLegalities.getHistoric() ) )
                                             .historicbrawl( LegalStatus.getFromLabel( tempLegalities.getHistoricbrawl() ) )
                                             .legacy( LegalStatus.getFromLabel( tempLegalities.getLegacy() ) )
                                             .modern( LegalStatus.getFromLabel( tempLegalities.getModern() ) )
                                             .oldschool( LegalStatus.getFromLabel( tempLegalities.getOldschool() ) )
                                             .pauper( LegalStatus.getFromLabel( tempLegalities.getPauper() ) )
                                             .paupercommander( LegalStatus.getFromLabel( tempLegalities.getPaupercommander() ) )
                                             .penny( LegalStatus.getFromLabel( tempLegalities.getPenny() ) )
                                             .pioneer( LegalStatus.getFromLabel( tempLegalities.getPioneer() ) )
                                             .premodern( LegalStatus.getFromLabel( tempLegalities.getPremodern() ) )
                                             .standard( LegalStatus.getFromLabel( tempLegalities.getStandard() ) )
                                             .vintage( LegalStatus.getFromLabel( tempLegalities.getVintage() ) )
                                             .id( scryfallCard.getId() + "_Legalities " ).build();
        card.setLegalities( newLegalities );
        card.setCardTypes( CardType.getCardTypeFromScryFallTypeLine( scryfallCard.getTypeLine() ).stream()
                                   .collect( Collectors.toSet() ) );

        card.addPrinting( createPrintingFromScryfallCard( scryfallCard ) );

        return card;
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
                     .filter( scryfallCard -> scryfallCard.getGames().contains( "paper" ) && scryfallCard.getLanguage()
                                                                                                         .equalsIgnoreCase( "en" ) )
                     .collect( Collectors.toList() );
        int cardsSize = cards.size();
        log.info( "Getting prices already in database" );
//        List<String> alreadyInDataBase = priceRepository.findCardIdsByTimestamp( timeStamp );
        List<String> alreadyInDataBase = new ArrayList<String>();
        log.info( "{} prices in database looking to add {} new cards.",
                  alreadyInDataBase.size(),
                  cards.size() );
        cards = cards.stream().filter( c -> !alreadyInDataBase.contains( c.getId() ) ).collect( Collectors.toList() );
        log.info( "{} was reduced down to {}",
                  cardsSize,
                  cards.size() );

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
