package com.rezatron.mtgprice.entity.wizards;

import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.dto.magic.wizards.Rarity;
import com.rezatron.mtgprice.entity.Legalities;
import com.rezatron.mtgprice.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "cards" )
public
class Card {
    @Id
    private String id;
    private String oracleId;
    private String name;
    private String typeLine;
    private String oracleText;
    private String cmc;
    private Set<CardType> cardTypes;
    private Set<Color> colors;
    private Set<Color> colorIdentity;
    private Legalities legalities;
    private String manaCost;
    private String mtgSet;
    private String mtgSetName;
    private LocalDate releasedAt;
    private String collectorNumber;
    private Rarity rarity;
    private Set<Price> prices;
    @CreatedDate
    public LocalDateTime createDate;
    @LastModifiedDate
    public LocalDateTime lastModifiedDate;


    @Override
    public
    boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return id.equals( card.id ) && name.equals( card.name ) && mtgSetName.equals( card.mtgSetName )
               && collectorNumber.equals( card.collectorNumber ) && rarity == card.rarity;
    }

    @Override
    public
    int hashCode() {
        return Objects.hash( id,
                             name,
                             mtgSetName,
                             collectorNumber,
                             rarity );
    }
}
