package com.rezatron.mtgprice.magic;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import com.rezatron.mtgprice.magic.scryfall.Legalities;
import com.rezatron.mtgprice.magic.wizards.Color;
import com.rezatron.mtgprice.magic.wizards.Rarity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Card" )
@ToString()
@EqualsAndHashCode( exclude = {"prices"} )
@Entity
@Slf4j
@JsonPropertyOrder( alphabetic = true )
public
class Card {
    @Id
    private String id;
    private String name;
    private String mtgSet;
    private String mtgSetName;
    private LocalDate releasedAt;
    private String typeLine;
    private String language;
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
    private String cmc;
    private String collectorNumber;
    @Enumerated( EnumType.STRING )
    private Rarity rarity;

    @OneToMany( mappedBy = "card",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY)
    private Set<Price> prices = new LinkedHashSet<>();

    @OneToOne( fetch = FetchType.LAZY,
               cascade = {CascadeType.ALL})
    @JoinColumn( name = "images_id" )
    private Images images;

    @OneToMany( mappedBy = "card",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY )
    private Set<CardFace> cardFaces = new LinkedHashSet<>();

    public
    Set<CardFace> getCardFaces() {
        return cardFaces;
    }

    public
    void setCardFaces(Set<CardFace> cardFaces) {
        this.cardFaces.clear();
        if (cardFaces != null) {
            this.cardFaces.addAll(cardFaces);
        }
    }

    public
    Images getImages() {
        return images;
    }

    public
    void setImages(Images images) {
        this.images = images;
    }

    public
    Set<Price> getPrices() {
        return prices;
    }

    public
    void setPrices(Set<Price> prices) {
        this.prices = prices;
    }
}
