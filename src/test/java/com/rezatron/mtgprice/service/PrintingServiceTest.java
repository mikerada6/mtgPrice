package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.repository.CardRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith( MockitoExtension.class )
class PrintingServiceTest {

    @Mock
    CardRepository cardRepository;
    @InjectMocks
    CardService cardService;


    }
