package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface DeckRepository extends JpaRepository<Deck, String> {
}
