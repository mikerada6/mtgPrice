package com.rezatron.mtgprice.magic.scryfall;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated( "net.hexar.json2pojo" )
@SuppressWarnings( "unused" )
public
class Datum {

    @SerializedName( "compressed_size" )
    private Long mCompressedSize;
    @SerializedName( "content_encoding" )
    private String mContentEncoding;
    @SerializedName( "content_type" )
    private String mContentType;
    @SerializedName( "description" )
    private String mDescription;
    @SerializedName( "download_uri" )
    private String mDownloadUri;
    @SerializedName( "id" )
    private String mId;
    @SerializedName( "name" )
    private String mName;
    @SerializedName( "object" )
    private String mObject;
    @SerializedName( "type" )
    private String mType;
    @SerializedName( "updated_at" )
    private String mUpdatedAt;
    @SerializedName( "uri" )
    private String mUri;

    public
    Long getCompressedSize() {
        return mCompressedSize;
    }

    public
    void setCompressedSize(Long compressedSize) {
        mCompressedSize = compressedSize;
    }

    public
    String getContentEncoding() {
        return mContentEncoding;
    }

    public
    void setContentEncoding(String contentEncoding) {
        mContentEncoding = contentEncoding;
    }

    public
    String getContentType() {
        return mContentType;
    }

    public
    void setContentType(String contentType) {
        mContentType = contentType;
    }

    public
    String getDescription() {
        return mDescription;
    }

    public
    void setDescription(String description) {
        mDescription = description;
    }

    public
    String getDownloadUri() {
        return mDownloadUri;
    }

    public
    void setDownloadUri(String downloadUri) {
        mDownloadUri = downloadUri;
    }

    public
    String getId() {
        return mId;
    }

    public
    void setId(String id) {
        mId = id;
    }

    public
    String getName() {
        return mName;
    }

    public
    void setName(String name) {
        mName = name;
    }

    public
    String getObject() {
        return mObject;
    }

    public
    void setObject(String object) {
        mObject = object;
    }

    public
    String getType() {
        return mType;
    }

    public
    void setType(String type) {
        mType = type;
    }

    public
    String getUpdatedAt() {
        return mUpdatedAt;
    }

    public
    void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public
    String getUri() {
        return mUri;
    }

    public
    void setUri(String uri) {
        mUri = uri;
    }

}
