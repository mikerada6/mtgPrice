package com.rezatron.mtgprice.controller;

import com.rezatron.mtgprice.service.UserService;
import com.rezatron.mtgprice.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class UserControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    @Test
    void createUser() {
        String userId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder().id( null ).firstName( "John" ).lastName( "Smith" ).userName( "dummy123" ).build();
        User _user = User.builder().id( userId ).firstName( "John" ).lastName( "Smith" ).userName( "dummy123" )
                         .createDateTime( now ).updateDateTime( now ).build();

        when( userService.saveUser( user ) ).thenReturn( _user );

        ResponseEntity response = userController.createUser( user );

        assertNotNull( response );
        assertEquals( HttpStatus.CREATED,
                      response.getStatusCode() );
        Object body = response.getBody();
        assert body != null;
        assertTrue( body instanceof User );
        User userBody = (User) body;
        assertEquals( _user,
                      userBody );

        verify( userService ).saveUser( user );

    }
}
