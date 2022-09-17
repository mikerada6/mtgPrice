package com.rezatron.mtgprice.dto.magic.scryfall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JsonIgnoreProperties( ignoreUnknown = true )
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode()
public
class ScryfallCardFace implements Comparable<ScryfallCardFace> {

    @SerializedName( "artist" )
    private String artist;
    @SerializedName( value = "artist_id",
                     alternate = {"artistId"} )
    private String artistId;
    @SerializedName( "colors" )
    private List<String> colors;
    @SerializedName( value = "illustration_id",
                     alternate = {"illustrationId"} )
    private String illustrationId;
    @SerializedName( value = "image_uris",
                     alternate = {"imageUris"} )
    private ImageUris imageUris;
    @SerializedName( value = "mana_cost",
                     alternate = {"manaCost"} )
    private String manaCost;
    @SerializedName( "name" )
    private String name;
    @SerializedName( "object" )
    private String object;
    @SerializedName( value = "oracle_text",
                     alternate = {"oracleText"} )
    private String oracleText;
    @SerializedName( value = "type_line",
                     alternate = {"typeLine"} )
    private String typeLine;

    @Override
    public
    int compareTo(
            @NotNull
            ScryfallCardFace o)
    {
        return name.compareTo( o.getName() );
    }
}
