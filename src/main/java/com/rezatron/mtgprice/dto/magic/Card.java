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

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Card" )
@ToString( exclude = {"prices", "inventories", "images","oracleText"} )
@EqualsAndHashCode( exclude = {"prices", "inventories", "images"} )
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
}
