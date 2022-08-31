
package com.rezatron.mtgprice.magic.scryfall;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Prices {

    @SerializedName("eur")
    private Double mEur;
    @SerializedName("eur_foil")
    private Double mEurFoil;
    @SerializedName("tix")
    private Double mTix;
    @SerializedName("usd")
    private Double mUsd;
    @SerializedName("usd_etched")
    private Double mUsdEtched;
    @SerializedName("usd_foil")
    private Double mUsdFoil;

    public Double getEur() {
        return mEur;
    }

    public Double getEurFoil() {
        return mEurFoil;
    }

    public Double getTix() {
        return mTix;
    }

    public Double getUsd() {
        return mUsd;
    }

    public Double getUsdEtched() {
        return mUsdEtched;
    }

    public Double getUsdFoil() {
        return mUsdFoil;
    }

    public static class Builder {

        private Double mEur;
        private Double mEurFoil;
        private Double mTix;
        private Double mUsd;
        private Double mUsdEtched;
        private Double mUsdFoil;

        public Prices.Builder withEur(Double eur) {
            mEur = eur;
            return this;
        }

        public Prices.Builder withEurFoil(Double eurFoil) {
            mEurFoil = eurFoil;
            return this;
        }

        public Prices.Builder withTix(Double tix) {
            mTix = tix;
            return this;
        }

        public Prices.Builder withUsd(Double usd) {
            mUsd = usd;
            return this;
        }

        public Prices.Builder withUsdEtched(Double usdEtched) {
            mUsdEtched = usdEtched;
            return this;
        }

        public Prices.Builder withUsdFoil(Double usdFoil) {
            mUsdFoil = usdFoil;
            return this;
        }

        public Prices build() {
            Prices prices = new Prices();
            prices.mEur = mEur;
            prices.mEurFoil = mEurFoil;
            prices.mTix = mTix;
            prices.mUsd = mUsd;
            prices.mUsdEtched = mUsdEtched;
            prices.mUsdFoil = mUsdFoil;
            return prices;
        }

    }

}
