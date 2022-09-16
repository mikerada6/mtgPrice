package com.rezatron.mtgprice.dto.magic;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Price",
        indexes = {@Index( name = "idx_price_card_id_timestamp",
                           columnList = "card_id, timestamp" ), @Index( name = "idx_price_timestamp",
                                                                        columnList = "timestamp" )},
        uniqueConstraints = {@UniqueConstraint( name = "uc_price_timestamp_card_id",
                                                columnNames = {"timestamp", "card_id"} )} )
@ToString
@EqualsAndHashCode( exclude = {"id"} )
@Entity
public
class Price {

    @Id
    @JsonIgnore
    private String id;
    private LocalDateTime timestamp;
    @Column( nullable = true )
    private Double eur;
    @Column( nullable = true )
    private Double eurFoil;
    @Column( nullable = true )
    private Double tix;
    @Column( nullable = true )
    private Double usd;
    @Column( nullable = true )
    private Double usdEtched;
    @Column( nullable = true )
    private Double usdFoil;

    @ManyToOne( cascade = CascadeType.ALL,
                optional = false )
    @JoinColumn( name = "card_id",
                 nullable = false )
    private Card card;

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
