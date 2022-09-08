package com.rezatron.mtgprice.dto.magic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Card_Face" )
@ToString( exclude = {"card"} )
@EqualsAndHashCode( exclude = {"card"} )
@Entity
@Slf4j
@JsonPropertyOrder( alphabetic = true )
public
class CardFace {


    @ElementCollection( fetch = FetchType.EAGER )
    @CollectionTable( name = "CardFaceColor",
                      joinColumns = @JoinColumn( name = "id" ) )
    @Enumerated( EnumType.STRING )
    Set<Color> colors;
    @Id
    @JsonIgnore
    private String id;
    private String manaCost;
    private String name;
    private String typeLine;
    @ElementCollection( fetch = FetchType.EAGER )
    @CollectionTable( name = "CardFaceTypes",
                      joinColumns = @JoinColumn( name = "id" ) )
    @Enumerated( EnumType.STRING )
    private Set<CardType> cardTypes;

    @ManyToOne( optional = false,
                fetch = FetchType.LAZY )
    @JoinColumn( name = "card_id",
                 nullable = false )
    private Card card;

    @OneToOne( fetch = FetchType.LAZY,
               cascade = {CascadeType.ALL} )
    @JoinColumn( name = "images_id" )
    private CardFaceImages images;

    public
    Card getCard() {
        return card;
    }

    public
    void setCard(Card card) {
        this.card = card;
    }

    @PrePersist
    protected
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        cardTypes = CardType.getCardTypeFromScryFallTypeLine( typeLine ).stream().collect( Collectors.toSet() );
    }

    @PreUpdate
    public
    void preUpdate() {
        cardTypes = CardType.getCardTypeFromScryFallTypeLine( typeLine ).stream().collect( Collectors.toSet() );
    }
}
