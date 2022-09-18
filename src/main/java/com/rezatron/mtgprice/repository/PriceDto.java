package com.rezatron.mtgprice.repository;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rezatron.mtgprice.entity.Price} entity
 */
@Data
public
class PriceDto implements Serializable {
    private final String cardId;
}
