package com.rezatron.mtgprice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "DeckListItem" )
@ToString( exclude = {"deck"} )
@EqualsAndHashCode( exclude = {"id", "deck"} )
@Entity
public
class DeckListItem {
    @Id
    private String id;

    private String cardName;
    private String oracle_id;
    @PositiveOrZero
    @Max( 4 )
    @Min( 0 )
    @Column( nullable = false )
    private int quantity;
    private boolean sideBoard;

    @ManyToOne( cascade = CascadeType.ALL,
                optional = false )
    @JoinColumn( name = "deck_id",
                 nullable = false )
    private Deck deck;

    public
    Deck getDeck() {
        return deck;
    }

    public
    void setDeck(Deck deck) {
        this.deck = deck;
    }

    @PrePersist
    protected
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
