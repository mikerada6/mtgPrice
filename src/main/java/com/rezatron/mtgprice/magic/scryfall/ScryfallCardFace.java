
package com.rezatron.mtgprice.magic.scryfall;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ScryfallCardFace implements Comparable<ScryfallCardFace>{

    @SerializedName("artist")
    private String mArtist;
    @SerializedName("artist_id")
    private String mArtistId;
    @SerializedName("colors")
    private List<String> mColors;
    @SerializedName("illustration_id")
    private String mIllustrationId;
    @SerializedName("image_uris")
    private ImageUris mImageUris;
    @SerializedName("mana_cost")
    private String mManaCost;
    @SerializedName("name")
    private String mName;
    @SerializedName("object")
    private String mObject;
    @SerializedName("oracle_text")
    private String mOracleText;
    @SerializedName("type_line")
    private String mTypeLine;

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getArtistId() {
        return mArtistId;
    }

    public void setArtistId(String artistId) {
        mArtistId = artistId;
    }

    public List<String> getColors() {
        return mColors;
    }

    public void setColors(List<String> colors) {
        mColors = colors;
    }

    public String getIllustrationId() {
        return mIllustrationId;
    }

    public void setIllustrationId(String illustrationId) {
        mIllustrationId = illustrationId;
    }

    public ImageUris getImageUris() {
        return mImageUris;
    }

    public void setImageUris(ImageUris imageUris) {
        mImageUris = imageUris;
    }

    public String getManaCost() {
        return mManaCost;
    }

    public void setManaCost(String manaCost) {
        mManaCost = manaCost;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getObject() {
        return mObject;
    }

    public void setObject(String object) {
        mObject = object;
    }

    public String getOracleText() {
        return mOracleText;
    }

    public void setOracleText(String oracleText) {
        mOracleText = oracleText;
    }

    public String getTypeLine() {
        return mTypeLine;
    }

    public void setTypeLine(String typeLine) {
        mTypeLine = typeLine;
    }

    @Override
    public
    int compareTo(
            @NotNull
            ScryfallCardFace o)
    {
        return mName.compareTo( o.getName() );
    }
}
