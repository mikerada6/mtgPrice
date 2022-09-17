package com.rezatron.mtgprice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "User" )
@ToString()
@EqualsAndHashCode( exclude = {"inventories", "decks"} )
@Entity
@Slf4j
@JsonPropertyOrder( {"userName", "firstName", "lastName"} )
public
class User {
    @Id
    @JsonIgnore
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @OneToMany( mappedBy = "user",
                cascade = CascadeType.ALL,
                orphanRemoval = true )
    private Set<Inventory> inventories = new LinkedHashSet<>();

    @OneToMany( mappedBy = "user",
                cascade = CascadeType.ALL,
                orphanRemoval = true )
    private Set<Deck> decks = new LinkedHashSet<>();

    public
    Set<Deck> getDecks() {
        return decks;
    }

    public
    void setDecks(Set<Deck> decks) {
        this.decks = decks;
    }

    public
    Set<Inventory> getInventories() {
        return inventories;
    }

    public
    void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    @PrePersist
    protected
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    @Override
    public
    String toString() {
        return "User{" + "id='" + id + '\'' + ", userName='" + userName + '\'' + ", firstName='" + firstName + '\''
               + ", lastName='" + lastName + '\'' + ", createDateTime=" + createDateTime + ", updateDateTime="
               + updateDateTime + '}';
    }
}
