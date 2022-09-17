package com.rezatron.mtgprice.inventory;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@ToString
@Slf4j
public
class BulkInventory {
    private String cardId;
    private String cardName;
    private String set;
    private String colletorNumber;
    private int normal;
    private int foil;
}
