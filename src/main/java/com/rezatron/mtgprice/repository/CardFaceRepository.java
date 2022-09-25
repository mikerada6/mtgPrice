package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.wizards.CardFace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public
interface CardFaceRepository extends JpaRepository<CardFace, String> {

    List<CardFace> findByCard_MtgSetOrderByCard_CollectorNumberAsc(String mtgSet);

    Optional<CardFace> findFirstByName(
            @NonNull
            String name);





}
