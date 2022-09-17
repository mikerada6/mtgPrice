package com.rezatron.mtgprice.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping( "/api/v1/users" )
@Slf4j
public
class UserController {

    @Autowired
    UserService userService;

    @Operation( summary = "This method will create a new user.",
                description = "This method will create a new user with the provided information.  No password is "
                              + "created." )
    @PostMapping( "/" )
    public
    ResponseEntity<User> createUser(
            @RequestBody( description = "Details for the user you wish to create.",
                          required = true,
                          content = @Content( schema = @Schema( implementation = User.class ) ) )
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            User user)
    {
        log.info( "createUser" );
        User u = userService.saveUser( user );

        return ResponseEntity.status( HttpStatus.CREATED ).body( u );
    }
}
