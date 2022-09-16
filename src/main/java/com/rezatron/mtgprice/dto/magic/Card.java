package com.rezatron.mtgprice.dto.magic;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.dto.magic.wizards.Rarity;
import com.rezatron.mtgprice.inventory.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Card",
        indexes = {@Index( name = "idx_card_mtgset",
                           columnList = "mtgSet" )} )
@ToString
@EqualsAndHashCode( exclude = {"prices", "inventories", "images", "decks"} )
@Entity
@Slf4j
@JsonPropertyOrder( alphabetic = true )
public
class Card implements Comparable<Card> {
    @Id
    private String id;
    private String name;
    private String mtgSet;
    private String mtgSetName;
    private LocalDate releasedAt;
    private String typeLine;
    private String language;
    private String cmc;
    private String collectorNumber;
    private String oracleId;
    @Lob
    private String oracleText;

    @ElementCollection( fetch = FetchType.EAGER )
    @CollectionTable( name = "ColorIdentity",
                      joinColumns = @JoinColumn( name = "id" ) )
    @Enumerated( EnumType.STRING )
    private Set<Color> colorIdentity;

    @ElementCollection( fetch = FetchType.EAGER )
    @CollectionTable( name = "Color",
                      joinColumns = @JoinColumn( name = "id" ) )
    @Enumerated( EnumType.STRING )
    private Set<Color> colors;

    @ElementCollection( fetch = FetchType.EAGER )
    @CollectionTable( name = "CardTypes",
                      joinColumns = @JoinColumn( name = "id" ) )
    @Enumerated( EnumType.STRING )
    private Set<CardType> cardTypes;

    @Enumerated( EnumType.STRING )
    private Rarity rarity;

    @OneToMany( mappedBy = "card",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY )
    private Set<Price> prices = new LinkedHashSet<>();

    @OneToOne( fetch = FetchType.LAZY,
               cascade = {CascadeType.ALL} )
    @JoinColumn( name = "images_id" )
    private Images images;

    @OneToMany( mappedBy = "card",
                cascade = CascadeType.ALL,
                orphanRemoval = true )
    private Set<CardFace> cardFaces = new LinkedHashSet<>();

    @OneToMany( mappedBy = "card",
                cascade = CascadeType.ALL,
                orphanRemoval = true )
    private Set<Inventory> inventories = new LinkedHashSet<>();


    public
    void setCardFaces(Set<CardFace> cardFaces) {
        this.cardFaces.clear();
        if (cardFaces != null) {
            this.cardFaces.addAll( cardFaces );
        }
    }


    @PreUpdate
    public
    void preUpdate() {
        cardTypes = CardType.getCardTypeFromScryFallTypeLine( typeLine ).stream().collect( Collectors.toSet() );
    }

    @Override
    public
    int compareTo(
            @NotNull
            Card o)
    {
        if (releasedAt.isBefore( o.getReleasedAt() )) {
            return -1;
        } else if (releasedAt.isAfter( o.getReleasedAt() )) {
            return 1;
        }
        if (getColorRank() < o.getColorRank()) {
            return -1;
        }
        if (getColorRank() > o.getColorRank()) {
            return 1;
        }
        return name.compareTo( o.getName() );
    }

    public
    int getColorRank()
    {
        if (colors.size() == 0 && !cardTypes.contains( CardType.LAND ) && !cardTypes.contains( CardType.ARTIFACT ))
            if (cardTypes.contains( CardType.LAND )) {
                return 9;
            }

        if (cardTypes.contains( CardType.BASICLAND )) {
            return 10;
        }
        if (colors.size() == 1) {
            if (colors.contains( Color.WHITE )) {
                return 2;
            }
            if (colors.contains( Color.BLUE )) {
                return 3;
            }
            if (colors.contains( Color.BLACK )) {
                return 4;
            }
            if (colors.contains( Color.RED )) {
                return 5;
            }
            if (colors.contains( Color.GREEN )) {
                return 6;
            }
        }
        if (colors.size() > 1) {
            return 7;
        }
        return 8;
    }

    @Override
    public
    String toString() {
        return "Card{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", mtgSet='" + mtgSet + '\'' + ", releasedAt="
               + releasedAt + ", typeLine='" + typeLine + '\'' + ", language='" + language + '\'' + ", cmc='" + cmc
               + '\'' + ", collectorNumber='" + collectorNumber + '\'' + ", oracleId='" + oracleId + '\''
               + ", oracleText='" + oracleText + '\'' + ", colorIdentity=" + colorIdentity + ", colors=" + colors
               + ", cardTypes=" + cardTypes + ", rarity=" + rarity + ", images=" + images + ", cardFaces=" + cardFaces
               + '}';
    }
}
