package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.magic.CardFace;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface CardFaceRepository extends JpaRepository<CardFace, String> {
}
