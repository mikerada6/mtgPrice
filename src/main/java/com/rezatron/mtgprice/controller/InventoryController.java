package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.dto.InventoryDto;
import com.rezatron.mtgprice.inventory.BulkInventory;
import com.rezatron.mtgprice.service.InventoryService;
import com.rezatron.mtgprice.service.UserService;
import com.rezatron.mtgprice.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/inventory" )
@Slf4j
public
class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    UserService userService;

    @GetMapping( "/user/{userId}" )
    public
    ResponseEntity getAll(
            @PathVariable( "userId" )
            String userId)
    {
        log.info( "testing" );
        List<BulkInventory> _inventory = inventoryService.getAll( userId );
        return ResponseEntity.status( HttpStatus.OK ).body( _inventory );
    }

    @Operation( summary = "This method will add a card to useres inventory .",
                description = "This method will create a new user with the provided information.  No password is "
                              + "created." )
    @PostMapping( "/user/{userId}" )
    public
    ResponseEntity addCard(
            @RequestBody( description = "Details for the user you wish to create.",
                          required = true,
                          content = @Content( schema = @Schema( implementation = InventoryDto.class ) ) )
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            InventoryDto inventoryDto,
            @PathVariable( "userId" )
            String userId)
    {
        log.info( "addCard" );
        User user = userService.findById( userId );
        if (user == null) {
            log.warn( "No user found with id {}",
                      userId );
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "No user with userid " + userId + "." );
        }
        inventoryDto.setUserId( user.getId() );
        InventoryDto _inventoryDto = inventoryService.addCard( inventoryDto,
                                                               user );
        return ResponseEntity.status( HttpStatus.CREATED ).body( _inventoryDto );
    }


    @PostMapping( "/user/{userId}/bulkAdd" )
    public
    ResponseEntity addBulkInventory(
            @RequestBody( description = "Cards to add.",
                          required = true,
                          content = @Content( schema = @Schema( implementation = BulkInventory.class ) ) )
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            List<BulkInventory> cards,
            @PathVariable( "userId" )
            String userId)
    {
        User user = userService.findById( userId );
        if (user == null) {
            log.warn( "No user found with id {}",
                      userId );
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "No user with userid " + userId + "." );
        }
        log.info( "bulkAdd" );
        List<InventoryDto> saved = inventoryService.addCards( cards,
                                                              user );
        return ResponseEntity.status( HttpStatus.CREATED ).body( saved );
    }
}
