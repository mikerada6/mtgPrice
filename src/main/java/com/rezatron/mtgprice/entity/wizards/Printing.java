package com.rezatron.mtgprice.entity.wizards;

import com.rezatron.mtgprice.dto.magic.wizards.Rarity;
import com.rezatron.mtgprice.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "printings" )
public
class Printing {
    @Id
    private String id;
    private String mtgSet;
    private String mtgSetName;
    private LocalDate releasedAt;
    private String collectorNumber;
    private Rarity rarity;
    private String scryfallId;
    private Set<Price> prices;

    public
    void addPrice(Price p) {
        if (prices == null) {
            prices = new HashSet<Price>();
        }
        prices.add( p );
    }
}
