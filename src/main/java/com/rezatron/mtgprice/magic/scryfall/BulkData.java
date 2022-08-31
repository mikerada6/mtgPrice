package com.rezatron.mtgprice.magic.scryfall;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated( "net.hexar.json2pojo" )
@SuppressWarnings( "unused" )
public
class BulkData {

    @SerializedName( "data" )
    private List<Datum> mData;
    @SerializedName( "has_more" )
    private Boolean mHasMore;
    @SerializedName( "object" )
    private String mObject;

    public
    List<Datum> getData() {
        return mData;
    }

    public
    void setData(List<Datum> data) {
        mData = data;
    }

    public
    Boolean getHasMore() {
        return mHasMore;
    }

    public
    void setHasMore(Boolean hasMore) {
        mHasMore = hasMore;
    }

    public
    String getObject() {
        return mObject;
    }

    public
    void setObject(String object) {
        mObject = object;
    }

}
