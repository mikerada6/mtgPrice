package com.rezatron.mtgprice.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rezatron.mtgprice.dto.LegalStatus;
import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.entity.Legalities;
import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.repository.CardRepository;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class CardServiceTest {

    @Mock
    CardRepository cardRepository;
    @InjectMocks
    CardService cardService;

    @Test
    void findByIdTest() throws IOException
    {
        ClassPathResource staticDataResource = new ClassPathResource( "trepanationBlade.json" );
        String json = IOUtils.toString( staticDataResource.getInputStream(),
                                        StandardCharsets.UTF_8 );
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter( LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter( LocalDate.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        Card trepanationBlade = gson.fromJson( json,
                                               Card.class );
        when( cardRepository.findById( "21a815ed-c8b4-4414-8b27-ea612e2977e2" ) ).thenReturn( Optional.of( trepanationBlade ) );

        Card returnedCard = cardService.findById( "21a815ed-c8b4-4414-8b27-ea612e2977e2" );

        assertEquals( trepanationBlade,
                      returnedCard );
    }
}
