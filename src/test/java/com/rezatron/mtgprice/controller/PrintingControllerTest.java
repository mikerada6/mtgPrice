package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.service.CardService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith( MockitoExtension.class )
class PrintingControllerTest {

    @Mock
    CardService cardService;
    @InjectMocks
    CardController cardController;

//    @Test
//    void getSuperTypes() {
//        when( cardService.getSuperTypes() ).thenReturn( Arrays.asList( "One",
//                                                                       "Two",
//                                                                       "Three" ) );
//
//
//        ResponseEntity response = cardController.getSuperTypes();
//
//        assertNotNull( response );
//        assertEquals( HttpStatus.OK,
//                      response.getStatusCode() );
//        Object body = response.getBody();
//        assert body != null;
//        assertTrue( body instanceof List );
//        List bodyList = (List) body;
//        assertEquals( 3,
//                      bodyList.size() );
//        assertTrue( bodyList.contains( "One" ) );
//        assertTrue( bodyList.contains( "Two" ) );
//        assertTrue( bodyList.contains( "Three" ) );
//
//        verify( cardService ).getSuperTypes();
//    }
}
