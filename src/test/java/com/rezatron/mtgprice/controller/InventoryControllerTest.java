package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.dto.InventoryDto;
import com.rezatron.mtgprice.inventory.BulkInventory;
import com.rezatron.mtgprice.service.InventoryService;
import com.rezatron.mtgprice.service.UserService;
import com.rezatron.mtgprice.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class InventoryControllerTest {

    @Mock
    InventoryService inventoryService;
    @Mock
    UserService userService;
    @InjectMocks
    InventoryController inventoryController;

    @Test
    void getAll() {
        String idA = UUID.randomUUID().toString();
        String idB = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        BulkInventory one = BulkInventory.builder().cardId( idA ).cardName( "cardNameA" ).set( "setA" )
                                         .colletorNumber( "colletorNumberA" ).normal( 1 ).foil( 1 ).build();
        BulkInventory two = BulkInventory.builder().cardId( idB ).cardName( "cardNameA" ).set( "setA" )
                                         .colletorNumber( "colletorNumberA" ).normal( 1 ).foil( 1 ).build();

        when( inventoryService.getAll( userId ) ).thenReturn( Arrays.asList( one,
                                                                             two ) );


        ResponseEntity response = inventoryController.getAll( userId );

        assertNotNull( response );
        assertEquals( HttpStatus.OK,
                      response.getStatusCode() );
        Object body = response.getBody();
        assert body != null;
        assertTrue( body instanceof List );
        List bodyList = (List) body;
        assertEquals( 2,
                      bodyList.size() );
        assertTrue( bodyList.contains( one ) );
        assertTrue( bodyList.contains( two ) );

        verify( inventoryService ).getAll( userId );
    }

    @Test
    public
    void addCard()
    {
        String id = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        String cardId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder().id(userId).build();
        InventoryDto dto = InventoryDto.builder().cardId( cardId ).userId( userId ).foil( false ).build();
        InventoryDto _dto =
                InventoryDto.builder().id(id).createDateTime( now ).updateDateTime( now ).cardId( cardId ).userId( userId ).foil( false ).build();

        when(inventoryService.addCard( dto,user )).thenReturn( _dto );
        when(userService.findById( userId )).thenReturn( user );

        ResponseEntity response = inventoryController.addCard( dto, userId );


        assertNotNull( response );
        assertEquals( HttpStatus.CREATED,
                      response.getStatusCode() );
        Object body = response.getBody();
        assert body != null;
        assertTrue( body instanceof InventoryDto );
        InventoryDto bodyList = (InventoryDto) body;
        assertEquals( _dto,
                      bodyList );


        verify( inventoryService ).addCard( dto,user );
        verify(userService).findById( userId );
    }
    @Test
    public
    void addCardNoUser()
    {
        String id = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        String cardId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder().id(userId).build();
        InventoryDto dto = InventoryDto.builder().cardId( cardId ).userId( userId ).foil( false ).build();
        InventoryDto _dto =
                InventoryDto.builder().id(id).createDateTime( now ).updateDateTime( now ).cardId( cardId ).userId( userId ).foil( false ).build();

        when(userService.findById( userId )).thenReturn( null );

        ResponseEntity response = inventoryController.addCard( dto, userId );


        assertNotNull( response );
        assertEquals( HttpStatus.UNAUTHORIZED,
                      response.getStatusCode() );
        Object body = response.getBody();
        assert body != null;
        assertTrue( body instanceof String );
        String bodyList = (String) body;
        assertEquals( "No user with userid " + userId+".",
                      bodyList );


        verify( inventoryService, never() ).addCard( dto, user );
        verify(userService).findById( userId );
    }

    @Test
    public
    void addBulkCard()
    {

        String idA = UUID.randomUUID().toString();
        String idB = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        BulkInventory one = BulkInventory.builder().cardId( idA ).cardName( "cardNameA" ).set( "setA" )
                                         .colletorNumber( "colletorNumberA" ).normal( 1 ).foil( 1 ).build();
        BulkInventory two = BulkInventory.builder().cardId( idB ).cardName( "cardNameA" ).set( "setA" )
                                         .colletorNumber( "colletorNumberA" ).normal( 1 ).foil( 1 ).build();
        List<BulkInventory> bulkInventoryCards = Arrays.asList( one,
                                                                two );

        String cardIdA = UUID.randomUUID().toString();
        String cardIdB = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder().id(userId).build();
        InventoryDto dtoA = InventoryDto.builder().cardId( cardIdA ).userId( userId ).foil( false ).build();
        InventoryDto dtoB = InventoryDto.builder().cardId( cardIdB ).userId( userId ).foil( true ).build();
        InventoryDto _dtoA =
                InventoryDto.builder().id(idA).createDateTime( now ).updateDateTime( now ).cardId( cardIdA ).userId( userId ).foil( false ).build();
        InventoryDto _dtoB =
                InventoryDto.builder().id(idB).createDateTime( now ).updateDateTime( now ).cardId( cardIdB ).userId( userId ).foil( true ).build();
        List<InventoryDto> cards = Arrays.asList( _dtoA,
                                                  _dtoB );


        when(inventoryService.addCards( bulkInventoryCards,user )).thenReturn( cards );
        when(userService.findById( userId )).thenReturn( user );

        ResponseEntity response = inventoryController.addBulkInventory( bulkInventoryCards, userId );


        assertNotNull( response );
        assertEquals( HttpStatus.CREATED,
                      response.getStatusCode() );
        Object body = response.getBody();
        assert body != null;
        assertTrue( body instanceof List );
        List<BulkInventory> bodyList = (List<BulkInventory>) body;
      assertEquals( 2,
                      bodyList.size() );
        assertTrue( bodyList.contains( _dtoA ) );
        assertTrue( bodyList.contains( _dtoB ) );

        verify(userService).findById( userId );
        verify(inventoryService).addCards( bulkInventoryCards,user );
    }

    @Test
    public
    void addBulkCardNoUser()
    {
        String idA = UUID.randomUUID().toString();
        String idB = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        BulkInventory one = BulkInventory.builder().cardId( idA ).cardName( "cardNameA" ).set( "setA" )
                                         .colletorNumber( "colletorNumberA" ).normal( 1 ).foil( 1 ).build();
        BulkInventory two = BulkInventory.builder().cardId( idB ).cardName( "cardNameA" ).set( "setA" )
                                         .colletorNumber( "colletorNumberA" ).normal( 1 ).foil( 1 ).build();
        List<BulkInventory> bulkInventoryCards = Arrays.asList( one,
                                                                two );

        String cardIdA = UUID.randomUUID().toString();
        String cardIdB = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder().id(userId).build();
        InventoryDto dtoA = InventoryDto.builder().cardId( cardIdA ).userId( userId ).foil( false ).build();
        InventoryDto dtoB = InventoryDto.builder().cardId( cardIdB ).userId( userId ).foil( true ).build();
        InventoryDto _dtoA =
                InventoryDto.builder().id(idA).createDateTime( now ).updateDateTime( now ).cardId( cardIdA ).userId( userId ).foil( false ).build();
        InventoryDto _dtoB =
                InventoryDto.builder().id(idB).createDateTime( now ).updateDateTime( now ).cardId( cardIdB ).userId( userId ).foil( true ).build();
        List<InventoryDto> cards = Arrays.asList( _dtoA,
                                                  _dtoB );


        when(userService.findById( userId )).thenReturn( null );

        ResponseEntity response = inventoryController.addBulkInventory( bulkInventoryCards, userId );


        assertNotNull( response );
        assertEquals( HttpStatus.UNAUTHORIZED,
                      response.getStatusCode() );
        Object body = response.getBody();
        assert body != null;
        assertTrue( body instanceof String );
        String bodyList = (String) body;
        assertEquals( "No user with userid " + userId+".",
                      bodyList );


        verify(inventoryService, never()).addCards( bulkInventoryCards,user );
        verify(userService).findById( userId );
    }
}
