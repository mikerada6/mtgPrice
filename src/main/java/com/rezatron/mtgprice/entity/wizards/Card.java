package com.rezatron.mtgprice.entity.wizards;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.entity.Legalities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cards")
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
    private Set<Printing> printings;


    public
    void addPrinting(Printing p) {
        if (printings == null) {
            printings = new HashSet<Printing>();
        }
        printings.add( p );
    }

}
