package com.rezatron.mtgprice.entity.wizards;

import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.entity.Legalities;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.PreUpdate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Data
@Builder
@ToString()
@Document( collection = "cards" )
@Slf4j
public
class Card {
    @Id
    private String id;
    private String name;
    private String typeLine;
    private String oracleText;
    private String cmc;
    private Set<CardType> cardTypes;
    private Set<Color> colors;
    private Set<Color> colorIdentity;
    private Legalities legalities;
    private List<Printing> printings;

    @PreUpdate
    public
    void preUpdate() {
        cardTypes = CardType.getCardTypeFromScryFallTypeLine( typeLine ).stream().collect( Collectors.toSet() );
    }

    public
    void addPrinting(Printing p) {
        if(printings==null)
        {
            printings = new ArrayList<Printing>();
        }
        printings.add(p);
    }
}
