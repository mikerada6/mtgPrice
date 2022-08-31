
package com.rezatron.mtgprice.magic.scryfall;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ImageUris {

    @SerializedName("art_crop")
    private String mArtCrop;
    @SerializedName("border_crop")
    private String mBorderCrop;
    @SerializedName("large")
    private String mLarge;
    @SerializedName("normal")
    private String mNormal;
    @SerializedName("png")
    private String mPng;
    @SerializedName("small")
    private String mSmall;

    public String getArtCrop() {
        return mArtCrop;
    }

    public void setArtCrop(String artCrop) {
        mArtCrop = artCrop;
    }

    public String getBorderCrop() {
        return mBorderCrop;
    }

    public void setBorderCrop(String borderCrop) {
        mBorderCrop = borderCrop;
    }

    public String getLarge() {
        return mLarge;
    }

    public void setLarge(String large) {
        mLarge = large;
    }

    public String getNormal() {
        return mNormal;
    }

    public void setNormal(String normal) {
        mNormal = normal;
    }

    public String getPng() {
        return mPng;
    }

    public void setPng(String png) {
        mPng = png;
    }

    public String getSmall() {
        return mSmall;
    }

    public void setSmall(String small) {
        mSmall = small;
    }

}
