package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.entity.Price;
import com.rezatron.mtgprice.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public
class PriceService {
    @Autowired
    PriceRepository priceRepository;

    @Transactional
    public
    List<Price> saveAll(List<Price> pricesToSave) {
        return priceRepository.saveAllAndFlush( pricesToSave );
    }

    @Transactional
    public
    Price save(Price price) {
        return priceRepository.saveAndFlush( price );
    }
}
