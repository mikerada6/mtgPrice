
package com.rezatron.mtgprice.dto.magic.scryfall;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@JsonIgnoreProperties( ignoreUnknown = true )
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode()
public class RelatedUris implements Serializable {

    @SerializedName("edhrec")
    private String edhrec;
    @SerializedName("gatherer")
    private String gatherer;
    @SerializedName("tcgplayer_infinite_articles")
    private String tcgplayerInfiniteArticles;
    @SerializedName("tcgplayer_infinite_decks")
    private String tcgplayerInfiniteDecks;

}
