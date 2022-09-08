package com.rezatron.mtgprice.dto.magic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "CardFaceImages" )
@ToString( exclude = {"cardFace"} )
@EqualsAndHashCode( exclude = {"cardFace"} )
@Entity
@JsonPropertyOrder( alphabetic = true )
public
class CardFaceImages {
    @Id
    @JsonIgnore
    private String id;
    private String artCrop;
    private String borderCrop;
    private String large;
    private String normal;
    private String png;
    private String small;

    @OneToOne( mappedBy = "images" )
    private CardFace cardFace;

    public
    CardFace getCard() {
        return cardFace;
    }

    public
    void setCard(CardFace card) {
        this.cardFace = card;
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
