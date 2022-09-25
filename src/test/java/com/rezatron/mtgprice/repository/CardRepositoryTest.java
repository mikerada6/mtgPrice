package com.rezatron.mtgprice.repository;


import com.rezatron.mtgprice.entity.wizards.Card;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


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
