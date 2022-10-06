package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.dto.PriceUpdate;
import com.rezatron.mtgprice.entity.Price;
import com.rezatron.mtgprice.entity.wizards.Printing;

import java.util.List;

public
interface CustomCardRepository {
    boolean addPriceToCard(String cardId, String printingId, Price price);

    boolean addPricesToCard(List<PriceUpdate> priceUpdateList);

    boolean addPrintingToCard(String cardId, Printing printing);

    boolean doesCardExist(String cardId);

    boolean doesPrintingExist(String printingId);

    boolean doesPriceExist(String priceId);

    List<String> findIdsNotInDatabase(List<String> ids);

    List<String> findPrintingIdsNotInDatabase(List<String> ids);
}
