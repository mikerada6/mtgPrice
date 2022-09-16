package com.rezatron.mtgprice.dto.magic.scryfall;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Generated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties( ignoreUnknown = true )
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode()
public
class ScryfallCard implements Serializable {

    @SerializedName( "artist" )
    private String artist;
    @SerializedName(value="artist_ids", alternate={"artistIds"})
    private List<String> artistIds;
    @SerializedName( "booster" )
    private Boolean booster;
    @SerializedName(value="border_color", alternate={"borderColor"})
    private String borderColor;
    @SerializedName(value="card_back_id", alternate={"cardBackId"})
    private String cardBackId;
    @SerializedName(value="cardmarket_id", alternate={"cardmarketId","cardMarketId"})
    private Long cardMarketId;
    @SerializedName( "cmc" )
    private String cmc;
    @SerializedName(value="collector_number", alternate={"collectorNumber"})
    private String collectorNumber;
    @SerializedName(value="color_identity", alternate={"colorIdentity"})
    private List<String> colorIdentity;
    @SerializedName( "colors" )
    private List<String> colors;
    @SerializedName( "digital" )
    private Boolean digital;
    @SerializedName(value="edhrec_rank", alternate={"edhrecRank"})
    private Long edhrecRank;
    @SerializedName( "finishes" )
    private List<String> finishes;
    @SerializedName(value="flavor_text", alternate={"flavorText"})
    private String flavorText;
    @SerializedName( "foil" )
    private Boolean foil;
    @SerializedName( "frame" )
    private String frame;
    @SerializedName(value="full_art", alternate={"fullArt"})
    private Boolean fullArt;
    @SerializedName( "games" )
    private List<String> games;
    @SerializedName(value="highres_image", alternate={"highresImage"})
    private Boolean highresImage;
    @SerializedName( "id" )
    private String id;
    @SerializedName(value="illustration_id", alternate={"illustrationId"})
    private String illustrationId;
    @SerializedName(value="image_status", alternate={"imageStatus"})
    private String imageStatus;
    @SerializedName(value="image_uris", alternate={"imageUris"})
    private ImageUris imageUris;
    @SerializedName( "keywords" )
    private List<Object> keywords;
    @SerializedName( "lang" )
    private String langauage;
    @SerializedName( "layout" )
    private String layout;
    @SerializedName( "legalities" )
    private Legalities legalities;
    @SerializedName(value="mana_cost", alternate={"manaCost"})
    private String manaCost;
    @SerializedName(value="mtgo_foil_id", alternate={"mtgoFoilId"})
    private Long mtgoFoilId;
    @SerializedName(value="mtgo_id", alternate={"mtgoId"})
    private Long mtgoId;
    @SerializedName(value="multiverse_ids", alternate={"multiverseIds"})
    private List<Long> multiverseIds;
    @SerializedName( "name" )
    private String name;
    @SerializedName( "nonfoil" )
    private Boolean nonfoil;
    @SerializedName( "object" )
    private String object;
    @SerializedName(value="oracle_id", alternate={"oracleId"})
    private String oracleId;
    @SerializedName(value="oracle_text", alternate={"oracleText"})
    private String oracleText;
    @SerializedName( "oversized" )
    private Boolean oversized;
    @SerializedName(value="penny_rank", alternate={"pennyRank"})
    private Long pennyRank;
    @SerializedName( "power" )
    private String power;
    @SerializedName( "prices" )
    private Prices prices;
    @SerializedName(value="prints_search_uri", alternate={"printsSearchUri"})
    private String printsSearchUri;
    @SerializedName( "promo" )
    private Boolean promo;
    @SerializedName( "rarity" )
    private String rarity;
    @SerializedName(value="related_uris", alternate={"relatedUris"})
    private RelatedUris relatedUris;
    @SerializedName(value="released_at", alternate={"releasedAt"})
    private String releasedAt;
    @SerializedName( "reprint" )
    private Boolean reprint;

    @SerializedName( "reserved" )
    private Boolean reserved;
    @SerializedName(value="rulings_uri", alternate={"rulingsUri"})
    private String rulingsUri;
    @SerializedName(value="scryfall_set_uri", alternate={"scryfallSetUri"})
    private String scryfallSetUri;
    @SerializedName(value="scryfall_uri", alternate={"scryfallUri"})
    private String scryfallUri;
    @SerializedName( "set" )
    private String set;
    @SerializedName(value="set_id", alternate={"setId"})
    private String setId;
    @SerializedName(value="set_name", alternate={"setName"})
    private String setName;
    @SerializedName(value="set_search_uri", alternate={"setSearchUri"})
    private String setSearchUri;
    @SerializedName(value="set_type", alternate={"setType"})
    private String setType;
    @SerializedName(value="set_uri", alternate={"setUri"})
    private String setUri;
    @SerializedName(value="story_spotlight", alternate={"storySpotlight"})
    private Boolean storySpotlight;
    @SerializedName(value="tcgplayer_id", alternate={"tcgplayerId"})
    private Long tcgplayerId;
    @SerializedName( "textless" )
    private Boolean textless;
    @SerializedName( "toughness" )
    private String toughness;
    @SerializedName(value="type_line", alternate={"typeLine"})
    private String typeLine;
    @SerializedName( "uri" )
    private String uri;
    @SerializedName( "variation" )
    private Boolean variation;

    @SerializedName(value="card_faces", alternate={"cardFaces"})
    private List<ScryfallCardFace> cardFaces;
    private String timeStamp;

}
