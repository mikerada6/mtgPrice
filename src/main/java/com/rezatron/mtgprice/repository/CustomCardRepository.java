package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.dto.PriceUpdate;
import com.rezatron.mtgprice.entity.Price;

import java.util.List;

public
interface CustomCardRepository {
    boolean addPriceToCard(String cardId, Price price);

    boolean addPricesToCard(List<PriceUpdate> priceUpdateList);

    boolean doesCardExist(String cardId);

    boolean doesPrintingExist(String printingId);

    boolean doesPriceExist(String priceId);

    List<String> findIdsNotInDatabase(List<String> ids);

}
