package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface PriceRepository extends JpaRepository<Price, String> {
}
