
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
public class ImageUris implements Serializable {

    @SerializedName(value="art_crop", alternate={"artCrop"})
    private String artCrop;
    @SerializedName(value="border_crop", alternate={"borderCrop"})
    private String borderCrop;
    @SerializedName("large")
    private String large;
    @SerializedName("normal")
    private String normal;
    @SerializedName("png")
    private String png;
    @SerializedName("small")
    private String small;


}
