package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.dto.InventoryDto;
import com.rezatron.mtgprice.service.InventoryService;
import com.rezatron.mtgprice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping( "/api/v1/inventory" )
@Slf4j
public
class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    UserService userService;
    @Operation( summary = "This method will add a card to useres inventory .",
                description = "This method will create a new user with the provided information.  No password is "
                              + "created." )
    @PostMapping( "/addCard" )
    public
    ResponseEntity<InventoryDto> addCard(
            @RequestBody( description = "Details for the user you wish to create.",
                          required = true,
                          content = @Content( schema = @Schema( implementation = InventoryDto.class ) ) )
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            InventoryDto inventoryDto)
    {
        log.info( "addCard" );
        InventoryDto _inventoryDto = inventoryService.addCard( inventoryDto );
        return ResponseEntity.status( HttpStatus.CREATED ).body( _inventoryDto );
    }
}
