package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public
class CardService {
    @Autowired
    CardRepository cardRepository;


    @Transactional( readOnly = true )
    public
    Card findById(String id) {
        log.info( "findById {}.",
                  id );
        return cardRepository.findById( id ).orElse( null );
    }

    @Transactional( readOnly = true )
    public
    List<String> findIdsNotInDatabase(List<String> ids) {
        return cardRepository.findIdsNotInDatabase( ids );
    }

    public
    long count() {
        return cardRepository.count();
    }
}
