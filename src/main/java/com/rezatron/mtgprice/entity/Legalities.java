package com.rezatron.mtgprice.entity;

import com.rezatron.mtgprice.dto.LegalStatus;
import com.rezatron.mtgprice.entity.wizards.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Legality" )
@ToString
@EqualsAndHashCode()
@Entity
@Slf4j
public
class Legalities {
    @Id
    private String id;
    @OneToOne( cascade = CascadeType.ALL,
               optional = false,
               orphanRemoval = true )
    @JoinColumn( name = "card_id",
                 nullable = false )
    private Card card;
    @Enumerated( EnumType.STRING )
    private LegalStatus alchemy;
    @Enumerated( EnumType.STRING )
    private LegalStatus brawl;
    @Enumerated( EnumType.STRING )
    private LegalStatus commander;
    @Enumerated( EnumType.STRING )
    private LegalStatus duel;
    @Enumerated( EnumType.STRING )
    private LegalStatus explorer;
    @Enumerated( EnumType.STRING )
    private LegalStatus future;
    @Enumerated( EnumType.STRING )
    private LegalStatus gladiator;
    @Enumerated( EnumType.STRING )
    private LegalStatus historic;
    @Enumerated( EnumType.STRING )
    private LegalStatus historicbrawl;
    @Enumerated( EnumType.STRING )
    private LegalStatus legacy;
    @Enumerated( EnumType.STRING )
    private LegalStatus modern;
    @Enumerated( EnumType.STRING )
    private LegalStatus oldschool;
    @Enumerated( EnumType.STRING )
    private LegalStatus pauper;
    @Enumerated( EnumType.STRING )
    private LegalStatus paupercommander;
    @Enumerated( EnumType.STRING )
    private LegalStatus penny;
    @Enumerated( EnumType.STRING )
    private LegalStatus pioneer;
    @Enumerated( EnumType.STRING )
    private LegalStatus premodern;
    @Enumerated( EnumType.STRING )
    private LegalStatus standard;
    @Enumerated( EnumType.STRING )
    private LegalStatus vintage;


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
    }
}
