package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.entity.Deck;
import com.rezatron.mtgprice.entity.DeckListItem;
import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.repository.CardRepository;
import com.rezatron.mtgprice.repository.DeckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public
class DeckService {

    @Autowired
    DeckRepository deckRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserService userService;

    public
    Deck createDeck(String deckList) {
        HashMap<String, Integer> deckListHashMap = new HashMap<String, Integer>();
        deckListHashMap.put( "Archmage's Charm",
                             2 );
        deckListHashMap.put( "Brazen Borrower",
                             1 );
        deckListHashMap.put( "Consider",
                             4 );
        Deck d = new Deck();
        Set<DeckListItem> deckListCards = new HashSet<>();
        for (String card : deckListHashMap.keySet()) {
            Card dbCard = cardRepository.findFirstByNameIgnoreCase( card ).orElse( null );
            if (dbCard != null) {
                deckListCards.add( DeckListItem.builder().cardName( card ).oracle_id( dbCard.getOracleId() )
                                               .quantity( deckListHashMap.get( card ) ).deck( d ).build() );
            }
        }
        d.setDeckList( deckListCards );
        d.setName( "Murktide Regent" );
        d.setUser( userService.findById( "ceb69f39-ab31-4e69-b8c1-6ebd00f4dbc7" ) );
        return deckRepository.save( d );
    }
}
