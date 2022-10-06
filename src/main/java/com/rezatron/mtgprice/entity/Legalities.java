package com.rezatron.mtgprice.entity;

import com.rezatron.mtgprice.dto.LegalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "legalities" )
public
class Legalities {
    @Id
    private String id;
    private LegalStatus alchemy;
    private LegalStatus brawl;
    private LegalStatus commander;
    private LegalStatus duel;
    private LegalStatus explorer;
    private LegalStatus future;
    private LegalStatus gladiator;
    private LegalStatus historic;
    private LegalStatus historicbrawl;
    private LegalStatus legacy;
    private LegalStatus modern;
    private LegalStatus oldschool;
    private LegalStatus pauper;
    private LegalStatus paupercommander;
    private LegalStatus penny;
    private LegalStatus pioneer;
    private LegalStatus premodern;
    private LegalStatus standard;
    private LegalStatus vintage;
}
