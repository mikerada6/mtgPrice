package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.service.FileService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith( MockitoExtension.class )
class ImageControllerTest {

    @Mock
    FileService fileService;
    @InjectMocks
    ImageController imageController;

//    @Test
//    void basicSetup() {
//        ReflectionTestUtils.setField( imageController,
//                                      "baseFileLocation",
//                                      "src/test/resources" );
//        when( fileService.createFolder( "src/test/resources/images/set" ) ).thenReturn( true );
//        when( fileService.createFolder( "src/test/resources/images/color" ) ).thenReturn( true );
//        when( fileService.createFolder( "src/test/resources/images/type" ) ).thenReturn( true );
//        when( fileService.createFolder( "src/test/resources/images/rarity" ) ).thenReturn( true );
//        when( fileService.createFolder( "src/test/resources/images/all" ) ).thenReturn( true );
//
//
//        ;
//        ResponseEntity response = imageController.basicSetup();
//
//        verify( fileService ).createFolder( "src/test/resources/images/set" );
//        verify( fileService ).createFolder( "src/test/resources/images/color" );
//        verify( fileService ).createFolder( "src/test/resources/images/type" );
//        verify( fileService ).createFolder( "src/test/resources/images/rarity" );
//        verify( fileService ).createFolder( "src/test/resources/images/all" );
//
//        verify( imageService ).saveAllImages();
//
//
//        assertNotNull( response );
//        assertEquals( HttpStatus.NO_CONTENT,
//                      response.getStatusCode() );
//
//        Object body = response.getBody();
//        assert body != null;
//        assertTrue( body instanceof String );
//        String bodyString = (String) body;
//        assertEquals( "Created image folder.",
//                      bodyString );
//    }

//    @Test
//    void basicSetupFail() {
//        ReflectionTestUtils.setField( imageController,
//                                      "baseFileLocation",
//                                      "src/test/resources" );
//        when( fileService.createFolder( "src/test/resources/images/set" ) ).thenReturn( true );
//        when( fileService.createFolder( "src/test/resources/images/color" ) ).thenReturn( true );
//        when( fileService.createFolder( "src/test/resources/images/type" ) ).thenReturn( true );
//        when( fileService.createFolder( "src/test/resources/images/rarity" ) ).thenReturn( true );
//        when( fileService.createFolder( "src/test/resources/images/all" ) ).thenReturn( false );
//
//
//        ResponseEntity response = imageController.basicSetup();
//
//        verify( fileService ).createFolder( "src/test/resources/images/set" );
//        verify( fileService ).createFolder( "src/test/resources/images/color" );
//        verify( fileService ).createFolder( "src/test/resources/images/type" );
//        verify( fileService ).createFolder( "src/test/resources/images/rarity" );
//        verify( fileService ).createFolder( "src/test/resources/images/all" );
//
//        verify( imageService,
//                never() ).saveAllImages();
//
//
//        assertNotNull( response );
//        assertEquals( HttpStatus.INTERNAL_SERVER_ERROR,
//                      response.getStatusCode() );
//
//        Object body = response.getBody();
//        assert body != null;
//        assertTrue( body instanceof String );
//        String bodyString = (String) body;
//        assertEquals( "Error creating folders.",
//                      bodyString );
//    }
}
