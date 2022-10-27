package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.dto.UserCreationDto;
import com.rezatron.mtgprice.entity.user.User;
import com.rezatron.mtgprice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public
class UserService {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public
    User createUser(UserCreationDto userCreationDto) {
//        Optional<User> user = userRepository.findById( userCreationDto.getUserName() );
//        if (user.isEmpty()) return null;
//        User newUser = User.builder().userName( userCreationDto.getUserName() )
//                           .build();
//        String encodedPassword = bCryptPasswordEncoder.encode(userCreationDto.getPassword());
//        newUser.setPassword( encodedPassword );
//        User _1newUser = userRepository.save( newUser );
//        _1newUser.setPassword( null );
//        return _1newUser;
        return null;
    }
}
