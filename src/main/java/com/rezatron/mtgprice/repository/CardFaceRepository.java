package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.dto.magic.CardFace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public
interface CardFaceRepository extends JpaRepository<CardFace, String> {

    List<CardFace> findByCard_MtgSetOrderByCard_CollectorNumberAsc(String mtgSet);


}
