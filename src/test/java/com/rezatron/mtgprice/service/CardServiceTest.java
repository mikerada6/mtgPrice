package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.entity.User;
import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class CardServiceTest {

    @Mock
    CardRepository cardRepository;
    @InjectMocks
    CardService cardService;


    @Test
    void findById() {
        String id = "0000579f-7b35-4ed3-b44c-db2a538066fe";
        Card card = Card.builder().name( "Fury Sliver" ).id( id ).build();

        when( cardRepository.findById( id ) ).thenReturn( Optional.of( card) );

        Card response = cardService.findById( id );

        assertNotNull( response );

        assertEquals( card,
                      response );

        verify( cardRepository ).findById( id );
    }

    @Test
    void updateCard() {
    }

    @Test
    void saveAll() {
    }

    @Test
    void getSuperTypes() {
    }

    @Test
    void save() {
    }
}
