package com.rezatron.mtgprice.magic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rezatron.mtgprice.magic.wizards.Color;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

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

    @ManyToOne( optional = false,
                fetch = FetchType.LAZY )
    @JoinColumn( name = "card_id",
                 nullable = false)
    private Card card;

    @OneToOne( fetch = FetchType.LAZY,
               cascade = {CascadeType.ALL})
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
    @PreUpdate
    protected
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
