package com.rezatron.mtgprice.entity.wizards;

import com.rezatron.mtgprice.dto.magic.wizards.CardType;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.entity.Legalities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "cards" )
@ToString
@EqualsAndHashCode()
@Slf4j
public
class OracleCard {
    @Id
    private String id;
    private String name;
    private String typeLine;
    private String oracleText;
    private String cmc;
    private Set<CardType> cardTypes;
    private Set<Color> colors;

    private Set<Color> colorIdentity;
    private Legalities legalities;

    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @PreUpdate
    public
    void preUpdate() {
        cardTypes = CardType.getCardTypeFromScryFallTypeLine( typeLine ).stream().collect( Collectors.toSet() );
    }
}
