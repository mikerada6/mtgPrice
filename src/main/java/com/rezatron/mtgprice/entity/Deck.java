package com.rezatron.mtgprice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Deck" )
@ToString
@EqualsAndHashCode( exclude = {"id"} )
@Entity
public
class Deck {
    @Id
    private String id;
    @NotEmpty
    @NotNull
    @Column( nullable = false )
    private String name;

    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @NotNull
    @ManyToOne( cascade = CascadeType.ALL,
                optional = false )
    @JoinColumn( name = "user_id",
                 nullable = false )
    private User user;

    @NotNull
    @OneToMany( mappedBy = "deck",
                cascade = CascadeType.ALL,
                orphanRemoval = true )
    private Set<DeckListItem> deckList = new LinkedHashSet<>();

    public
    void addDeckListItem(DeckListItem deckListItem)
    {
        deckListItem.setDeck( this );
        deckList.add( deckListItem );
    }


    @PrePersist
    protected
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
