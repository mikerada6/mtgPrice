
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
public class Prices implements Serializable {

    @SerializedName("eur")
    private Double eur;
    @SerializedName(value="eur_foil", alternate={"eurFoil"})
    private Double eurFoil;
    @SerializedName("tix")
    private Double tix;
    @SerializedName("usd")
    private Double usd;
    @SerializedName(value="usd_etched", alternate={"usdEtched"})
    private Double usdEtched;
    @SerializedName(value="usd_foil", alternate={"usdFoil"})
    private Double usdFoil;


}
