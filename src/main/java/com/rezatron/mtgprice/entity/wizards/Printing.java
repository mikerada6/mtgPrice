package com.rezatron.mtgprice.entity.wizards;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rezatron.mtgprice.dto.magic.wizards.Rarity;
import com.rezatron.mtgprice.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@ToString()
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "printings" )
@Slf4j
@JsonInclude( JsonInclude.Include.NON_NULL)
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
        if(prices==null)
        {
            prices = new HashSet<Price>();
        }
        prices.add(p);
    }
}
