package com.rezatron.mtgprice.entity.wizards;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.entity.Legalities;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.PreUpdate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@ToString()
@Document( collection = "cards" )
@Slf4j
@JsonInclude( JsonInclude.Include.NON_NULL)
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

    @PreUpdate
    public
    void preUpdate() {
        cardTypes = CardType.getCardTypeFromScryFallTypeLine( typeLine ).stream().collect( Collectors.toSet() );
    }

    public
    void addPrinting(Printing p) {
        if (printings == null) {
            printings = new HashSet<Printing>();
        }
        printings.add( p );
    }

}
