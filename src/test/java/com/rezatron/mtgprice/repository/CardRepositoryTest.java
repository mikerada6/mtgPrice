package com.rezatron.mtgprice.repository;


import com.rezatron.mtgprice.entity.wizards.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CardRepositoryTest {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardFaceRepository cardFaceRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    DeckRepository deckRepository;
    //    @Autowired
//    private TestEntityManager entityManager;
    private Card card;

    @Test
    void findAll() {
        List<Card> cards = cardRepository.findAll();
        assertTrue( cards.isEmpty() );
    }
}
