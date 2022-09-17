package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.queue.QueueSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith( MockitoExtension.class )
class TesteControllerTest {

    @Mock
    QueueSender queueSender;
    @InjectMocks
    TesteController testeController;

    @Test
    void send() {

        String response = testeController.send();

        assertEquals( "ok. done",
                      response );

        verify( queueSender ).send( "test message" );
    }
}
