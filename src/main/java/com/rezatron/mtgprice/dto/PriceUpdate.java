package com.rezatron.mtgprice.dto;

import com.rezatron.mtgprice.entity.Price;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public
class PriceUpdate {
    private String cardId;
    private Price price;
}
