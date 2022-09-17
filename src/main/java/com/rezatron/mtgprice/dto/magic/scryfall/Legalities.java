package com.rezatron.mtgprice.dto.magic.scryfall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties( ignoreUnknown = true )
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode()
public
class Legalities {

    @SerializedName( "alchemy" )
    private String alchemy;
    @SerializedName( "brawl" )
    private String brawl;
    @SerializedName( "commander" )
    private String commander;
    @SerializedName( "duel" )
    private String duel;
    @SerializedName( "explorer" )
    private String explorer;
    @SerializedName( "future" )
    private String future;
    @SerializedName( "gladiator" )
    private String gladiator;
    @SerializedName( "historic" )
    private String historic;
    @SerializedName( "historicbrawl" )
    private String historicbrawl;
    @SerializedName( "legacy" )
    private String legacy;
    @SerializedName( "modern" )
    private String modern;
    @SerializedName( "oldschool" )
    private String oldschool;
    @SerializedName( "pauper" )
    private String pauper;
    @SerializedName( "paupercommander" )
    private String paupercommander;
    @SerializedName( "penny" )
    private String penny;
    @SerializedName( "pioneer" )
    private String pioneer;
    @SerializedName( "premodern" )
    private String premodern;
    @SerializedName( "standard" )
    private String standard;
    @SerializedName( "vintage" )
    private String vintage;

}
