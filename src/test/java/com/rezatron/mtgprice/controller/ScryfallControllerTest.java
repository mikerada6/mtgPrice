package com.rezatron.mtgprice.controller;

import com.google.gson.JsonSyntaxException;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import com.rezatron.mtgprice.service.CardService;
import com.rezatron.mtgprice.service.FileService;
import com.rezatron.mtgprice.service.ScryfallService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class ScryfallControllerTest {

    @Mock
    CardService cardService;
    @Mock
    FileService fileService;
    @Mock
    ScryfallService scryfallService;
    @InjectMocks
    ScryfallController scryfallController;

    @SneakyThrows
    @Test
    void bulkDataUpdateNoFileFailedToLoad() {

        String fileLocation = "src/test/resources/2022-09-16T09-05-26.json";

        when( scryfallService.downloadData() ).thenReturn( fileLocation );
        when( fileService.loadFile( fileLocation ) ).thenThrow( IOException.class );

        ResponseEntity response = scryfallController.bulkDataUpdate( null );

        assertNotNull( response );
        assertEquals( HttpStatus.INTERNAL_SERVER_ERROR,
                      response.getStatusCode() );
        Object body = response.getBody();
        assert body != null;
        assertTrue( body instanceof String );
        String stringBody = (String) body;
        assertEquals( "Unable to read.",
                      stringBody );

        verify( scryfallService ).downloadData();
        verify( fileService ).loadFile( fileLocation );
    }

    @SneakyThrows
    @Test
    void bulkDataUpdateNoFile() {

        String fileLocation = "src/test/resources/2022-09-16T09-05-26.json";

        String twoCard = loadFile( "src/test/resources/twoCard.json" );
        assertNotNull( twoCard );

        ScryfallCard furySliver = ScryfallCard.builder().object( "card" ).id( "0000579f-7b35-4ed3-b44c-db2a538066fe" )
                                              .oracleId( "44623693-51d6-49ad-8cd7-140505caf02f" )
                                              .multiverseIds( Arrays.asList( 109722L ) ).mtgoId( 25527L )
                                              .mtgoFoilId( 14240L ).tcgplayerId( 14240L ).cardMarketId( 13850L )
                                              .name( "Fury Sliver" ).language( "en" ).build();

        ScryfallCard WestvaleAbbey = ScryfallCard.builder().object( "card" )
                                                 .id( "b245e80e-a113-4f34-a089-e3e514eaddc0" )
                                                 .oracleId( "04eeb9ad-5c59-411b-8809-db8349838588" )
                                                 .multiverseIds( Arrays.asList() ).tcgplayerId( 116919L )
                                                 .cardMarketId( 289305L )
                                                 .name( "Westvale Abbey // Ormendahl, Profane Prince" ).language( "en" )
                                                 .build();
        List<ScryfallCard> scryfallCards = Arrays.asList( furySliver,
                                                          WestvaleAbbey );

        when( scryfallService.downloadData() ).thenReturn( fileLocation );
        when( fileService.loadFile( fileLocation ) ).thenReturn( twoCard );
        when( scryfallService.convertDTO( twoCard ) ).thenReturn( scryfallCards );

        ResponseEntity response = scryfallController.bulkDataUpdate( null );

        assertNotNull( response );
        assertEquals( HttpStatus.ACCEPTED,
                      response.getStatusCode() );
        Object body = response.getBody();
        assert body != null;

        assertEquals( 2,
                      body );


        verify( scryfallService ).downloadData();
        verify( fileService ).loadFile( fileLocation );
        verify( scryfallService ).convertDTO( twoCard );

        verify( scryfallService ).sendMessages( scryfallCards,
                                                fileLocation );
    }

    @SneakyThrows
    @Test
    void bulkDataUpdateNoFileErrorConvertDTO() {

        String fileLocation = "src/test/resources/2022-09-16T09-05-26.json";

        String twoCard = loadFile( "src/test/resources/twoCard.json" );
        assertNotNull( twoCard );

        ScryfallCard furySliver = ScryfallCard.builder().object( "card" ).id( "0000579f-7b35-4ed3-b44c-db2a538066fe" )
                                              .oracleId( "44623693-51d6-49ad-8cd7-140505caf02f" )
                                              .multiverseIds( Arrays.asList( 109722L ) ).mtgoId( 25527L )
                                              .mtgoFoilId( 14240L ).tcgplayerId( 14240L ).cardMarketId( 13850L )
                                              .name( "Fury Sliver" ).language( "en" ).build();

        ScryfallCard WestvaleAbbey = ScryfallCard.builder().object( "card" )
                                                 .id( "b245e80e-a113-4f34-a089-e3e514eaddc0" )
                                                 .oracleId( "04eeb9ad-5c59-411b-8809-db8349838588" )
                                                 .multiverseIds( Arrays.asList() ).tcgplayerId( 116919L )
                                                 .cardMarketId( 289305L )
                                                 .name( "Westvale Abbey // Ormendahl, Profane Prince" ).language( "en" )
                                                 .build();
        List<ScryfallCard> scryfallCards = Arrays.asList( furySliver,
                                                          WestvaleAbbey );

        when( scryfallService.downloadData() ).thenReturn( fileLocation );
        when( fileService.loadFile( fileLocation ) ).thenReturn( twoCard );
        when( scryfallService.convertDTO( twoCard ) ).thenThrow( JsonSyntaxException.class );

        ResponseEntity response = scryfallController.bulkDataUpdate( null );

        assertNotNull( response );
        assertEquals( HttpStatus.INTERNAL_SERVER_ERROR,
                      response.getStatusCode() );
        Object body = response.getBody();
        assert body != null;
        assertTrue( body instanceof String );
        String bodyString = (String) body;
        assertEquals( "Ran into a problem trying to convert the ScryFall cards.",
                      body );


        verify( scryfallService ).downloadData();
        verify( fileService ).loadFile( fileLocation );
        verify( scryfallService ).convertDTO( twoCard );

        verify( scryfallService,
                never() ).sendMessages( scryfallCards,
                                        fileLocation );
    }

    private
    String loadFile(String file) throws IOException
    {
        try {
            return Files.readString( Path.of( file ) );
        } catch (IOException e) {
            throw e;
        }
    }
}
