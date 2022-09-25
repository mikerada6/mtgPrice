package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.wizards.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public
interface CardRepository extends JpaRepository<Card, String> {
    @Query( "SELECT DISTINCT c.mtgSet FROM Card c" )
    public
    List<String> findDistinctSets();

    @Query( "SELECT DISTINCT c.typeLine FROM Card c" )
    public
    List<String> findDistinctTypeLines();

    List<Card> findByIdIn(
            @NonNull
            Collection<String> ids);

    Optional<Card> findFirstByName(
            @NonNull
            String name);


    List<Card> findByMtgSetOrderByCollectorNumberAsc(String mtgSet);

    Optional<Card> findFirstByNameIgnoreCase(String name);


}
