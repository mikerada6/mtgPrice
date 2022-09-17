package com.rezatron.mtgprice.inventory;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.rezatron.mtgprice.dto.magic.Card;
import com.rezatron.mtgprice.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Inventory" )
@ToString( exclude = {"user"} )
@EqualsAndHashCode( exclude = {"user"} )
@Entity
@Slf4j
//@JsonPropertyOrder({ "userName", "firstName", "lastName" })
public
class Inventory {
    @Id
    @JsonIgnore
    private String id;

    @ManyToOne( cascade = CascadeType.ALL,
                optional = false )
    @JoinColumn( name = "card_id",
                 nullable = false )
    private Card card;

    @ManyToOne( cascade = CascadeType.ALL,
                optional = false )
    @JoinColumn( name = "user_id",
                 nullable = false )
    private User user;

    private boolean foil;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @PrePersist
    protected
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    @JsonGetter
    public
    boolean isFoil()
    {
        return foil;
    }

    @JsonSetter
    public
    void setFoil(boolean foil) {
        this.foil = foil;
    }

    public
    String getCardId()
    {
        return card.getId();
    }

}
