package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.repository.CardRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith( MockitoExtension.class )
class PrintingServiceTest {

    @Mock
    CardRepository cardRepository;
    @InjectMocks
    CardService cardService;


}
