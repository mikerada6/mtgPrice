package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.repository.UserRepository;
import com.rezatron.mtgprice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public
class UserService {

    @Autowired
    UserRepository userRepository;


    @Transactional( readOnly = true )
    public
    User findById(String id) {
        log.info( "findById {}.",
                  id );
        return userRepository.findById( id ).orElse( null );
    }

    public
    User saveUser(User u)
    {
        return userRepository.saveAndFlush( u );
    }
}
