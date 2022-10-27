package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.dto.UserCreationDto;
import com.rezatron.mtgprice.entity.user.User;
import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping( "/api/v1/users" )
@Slf4j
public
class UserController {

    @Autowired
    UserService userService;


    @Operation( summary = "This method will create a new user.",
                description = "This method will create a new user given the user name and password given.." )
    @PostMapping( "/" )
    public
    ResponseEntity clearCart(
            @RequestBody( description = "New user data.",
                          required = true,
                          content = @Content( schema = @Schema( implementation = UserCreationDto.class ) ) )
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            UserCreationDto userCreationDto)
    {
        log.info( "UserCreationDto: {}", userCreationDto.getUserName() );
        User user = userService.createUser( userCreationDto );
        return ResponseEntity.status( HttpStatus.OK ).body( "user" );
    }
}
