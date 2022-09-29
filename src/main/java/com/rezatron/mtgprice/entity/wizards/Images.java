package com.rezatron.mtgprice.entity.wizards;

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
@Table( name = "Images" )
@ToString( exclude = {"card"} )
@EqualsAndHashCode( exclude = {"card"} )
@Entity
@JsonPropertyOrder( alphabetic = true )
public
class Images {
    @Id
    @JsonIgnore
    private String id;
    private String artCrop;
    private String borderCrop;
    private String large;
    private String normal;
    private String png;
    private String small;



    @PrePersist
    @PreUpdate
    protected
    void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
