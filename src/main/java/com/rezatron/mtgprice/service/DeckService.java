package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.entity.Deck;
import com.rezatron.mtgprice.entity.DeckListItem;
import com.rezatron.mtgprice.entity.User;
import com.rezatron.mtgprice.exception.UnknownCardException;
import com.rezatron.mtgprice.repository.CardRepository;
import com.rezatron.mtgprice.repository.DeckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    CardService cardService;

    public
    Deck createDeck(String deckList, String deckName, User u) {
        Deck d = new Deck();
        d.setName( deckName );
        d.setUser( u );
        String[] deckListArray = deckList.split( "\n" );
        for (String mainBoardCard : deckListArray) {
            if (mainBoardCard.equals( "" )) {
                break;
            }
            String[] tokens = mainBoardCard.split( " ",
                                                   2 );
            int quantity = 0;
            try {
                quantity = Integer.parseInt( tokens[0] );
            } catch (NumberFormatException e) {
                log.error( "Could not parse {} into a number",
                           tokens[0] );
                throw new UnknownCardException(
                        "Could not parse " + tokens[0] + " into a number for card " + mainBoardCard );

            }
            String oracleId = cardService.getOracleIdFromName( tokens[1] );
            if (oracleId == null) {
                log.warn( "Was given a card with name {} unable to find it in our database",
                          mainBoardCard );
                throw new UnknownCardException( "Unknown card with name " + mainBoardCard + "." );
            }
            DeckListItem dli = DeckListItem.builder().cardName( mainBoardCard ).quantity( quantity ).sideBoard( false )
                                           .oracle_id( oracleId ).build();
            d.addDeckListItem( dli );
        }

        boolean sideBoard = false;
        for (String sideBoardCard : deckListArray) {
            if (sideBoardCard.equals( "" )) {
                sideBoard = true;
                continue;
            }
            if (!sideBoard) {
                continue;
            }

            String[] tokens = sideBoardCard.split( " ",
                                                   2 );
            int quantity = 0;
            try {
                quantity = Integer.parseInt( tokens[0] );
            } catch (NumberFormatException e) {
                log.error( "Could not parse {} into a number",
                           tokens[0] );
                throw new UnknownCardException(
                        "Could not parse " + tokens[0] + " into a number for card " + sideBoardCard );

            }
            String oracleId = cardService.getOracleIdFromName( tokens[1] );
            if (oracleId == null) {
                log.warn( "Was given a card with name {} unable to find it in our database",
                          sideBoardCard );
                throw new UnknownCardException( "Unknown card with name " + sideBoardCard + "." );
            }
            DeckListItem dli = DeckListItem.builder().cardName( sideBoardCard ).quantity( quantity ).sideBoard( true )
                                           .oracle_id( oracleId ).build();
            d.addDeckListItem( dli );
        }


        return deckRepository.save( d );
    }
}
