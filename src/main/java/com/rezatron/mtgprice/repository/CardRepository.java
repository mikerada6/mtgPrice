package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.magic.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public
interface CardRepository extends JpaRepository<Card, String> {
    @Query("SELECT DISTINCT c.mtgSet FROM Card c")
    public
    List<String> findDistinctSets();



    List<Card> findByMtgSetOrderByCollectorNumberAsc(String mtgSet);


}
