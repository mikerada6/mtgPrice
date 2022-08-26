package com.rezatron.mtgprice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class AliveControllerTest {

    @Mock
    BuildProperties buildProperties;
    @InjectMocks
    AliveController aliveController;

    @Test
    void alive() {
        ResponseEntity<String> response = aliveController.alive();

        assertNotNull( response );
        assertEquals( HttpStatus.OK,
                      response.getStatusCode() );
        String isAlive = response.getBody();
        assertNotNull( isAlive );
        assertEquals( "alive",
                      isAlive );
    }

    @Test
    void version() {
        Instant time = Instant.now();
        when( buildProperties.getVersion() ).thenReturn( "1.0.0" );
        when( buildProperties.getTime() ).thenReturn( time );

        ResponseEntity<HashMap<String, String>> response = aliveController.version();

        assertNotNull( response );
        assertEquals( HttpStatus.OK,
                      response.getStatusCode() );
        HashMap<String, String> body = response.getBody();
        assert body != null;
        assertTrue( body.containsKey( "version" ) );
        assertEquals( "1.0.0",
                      body.get( "version" ) );
        assertTrue( body.containsKey( "time" ) );
        assertEquals( time.toString(),
                      body.get( "time" ) );
        assertTrue( body.containsKey( "baseFileLocation" ) );
//        assertEquals("testValue",
//                      body.get( "baseFileLocation" ) );
    }
}
