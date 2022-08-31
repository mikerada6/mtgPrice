package com.rezatron.mtgprice.magic.scryfall;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public
class BulkDataInformation {

    private String url;
    private LocalDateTime timestamp;
}
