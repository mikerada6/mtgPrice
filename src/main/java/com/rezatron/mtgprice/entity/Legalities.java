package com.rezatron.mtgprice.entity;

import com.rezatron.mtgprice.dto.LegalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
