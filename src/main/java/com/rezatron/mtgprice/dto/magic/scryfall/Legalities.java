
package com.rezatron.mtgprice.dto.magic.scryfall;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Legalities {

    @SerializedName("alchemy")
    private String mAlchemy;
    @SerializedName("brawl")
    private String mBrawl;
    @SerializedName("commander")
    private String mCommander;
    @SerializedName("duel")
    private String mDuel;
    @SerializedName("explorer")
    private String mExplorer;
    @SerializedName("future")
    private String mFuture;
    @SerializedName("gladiator")
    private String mGladiator;
    @SerializedName("historic")
    private String mHistoric;
    @SerializedName("historicbrawl")
    private String mHistoricbrawl;
    @SerializedName("legacy")
    private String mLegacy;
    @SerializedName("modern")
    private String mModern;
    @SerializedName("oldschool")
    private String mOldschool;
    @SerializedName("pauper")
    private String mPauper;
    @SerializedName("paupercommander")
    private String mPaupercommander;
    @SerializedName("penny")
    private String mPenny;
    @SerializedName("pioneer")
    private String mPioneer;
    @SerializedName("premodern")
    private String mPremodern;
    @SerializedName("standard")
    private String mStandard;
    @SerializedName("vintage")
    private String mVintage;

    public String getAlchemy() {
        return mAlchemy;
    }

    public String getBrawl() {
        return mBrawl;
    }

    public String getCommander() {
        return mCommander;
    }

    public String getDuel() {
        return mDuel;
    }

    public String getExplorer() {
        return mExplorer;
    }

    public String getFuture() {
        return mFuture;
    }

    public String getGladiator() {
        return mGladiator;
    }

    public String getHistoric() {
        return mHistoric;
    }

    public String getHistoricbrawl() {
        return mHistoricbrawl;
    }

    public String getLegacy() {
        return mLegacy;
    }

    public String getModern() {
        return mModern;
    }

    public String getOldschool() {
        return mOldschool;
    }

    public String getPauper() {
        return mPauper;
    }

    public String getPaupercommander() {
        return mPaupercommander;
    }

    public String getPenny() {
        return mPenny;
    }

    public String getPioneer() {
        return mPioneer;
    }

    public String getPremodern() {
        return mPremodern;
    }

    public String getStandard() {
        return mStandard;
    }

    public String getVintage() {
        return mVintage;
    }

    public static class Builder {

        private String mAlchemy;
        private String mBrawl;
        private String mCommander;
        private String mDuel;
        private String mExplorer;
        private String mFuture;
        private String mGladiator;
        private String mHistoric;
        private String mHistoricbrawl;
        private String mLegacy;
        private String mModern;
        private String mOldschool;
        private String mPauper;
        private String mPaupercommander;
        private String mPenny;
        private String mPioneer;
        private String mPremodern;
        private String mStandard;
        private String mVintage;

        public Legalities.Builder withAlchemy(String alchemy) {
            mAlchemy = alchemy;
            return this;
        }

        public Legalities.Builder withBrawl(String brawl) {
            mBrawl = brawl;
            return this;
        }

        public Legalities.Builder withCommander(String commander) {
            mCommander = commander;
            return this;
        }

        public Legalities.Builder withDuel(String duel) {
            mDuel = duel;
            return this;
        }

        public Legalities.Builder withExplorer(String explorer) {
            mExplorer = explorer;
            return this;
        }

        public Legalities.Builder withFuture(String future) {
            mFuture = future;
            return this;
        }

        public Legalities.Builder withGladiator(String gladiator) {
            mGladiator = gladiator;
            return this;
        }

        public Legalities.Builder withHistoric(String historic) {
            mHistoric = historic;
            return this;
        }

        public Legalities.Builder withHistoricbrawl(String historicbrawl) {
            mHistoricbrawl = historicbrawl;
            return this;
        }

        public Legalities.Builder withLegacy(String legacy) {
            mLegacy = legacy;
            return this;
        }

        public Legalities.Builder withModern(String modern) {
            mModern = modern;
            return this;
        }

        public Legalities.Builder withOldschool(String oldschool) {
            mOldschool = oldschool;
            return this;
        }

        public Legalities.Builder withPauper(String pauper) {
            mPauper = pauper;
            return this;
        }

        public Legalities.Builder withPaupercommander(String paupercommander) {
            mPaupercommander = paupercommander;
            return this;
        }

        public Legalities.Builder withPenny(String penny) {
            mPenny = penny;
            return this;
        }

        public Legalities.Builder withPioneer(String pioneer) {
            mPioneer = pioneer;
            return this;
        }

        public Legalities.Builder withPremodern(String premodern) {
            mPremodern = premodern;
            return this;
        }

        public Legalities.Builder withStandard(String standard) {
            mStandard = standard;
            return this;
        }

        public Legalities.Builder withVintage(String vintage) {
            mVintage = vintage;
            return this;
        }

        public Legalities build() {
            Legalities legalities = new Legalities();
            legalities.mAlchemy = mAlchemy;
            legalities.mBrawl = mBrawl;
            legalities.mCommander = mCommander;
            legalities.mDuel = mDuel;
            legalities.mExplorer = mExplorer;
            legalities.mFuture = mFuture;
            legalities.mGladiator = mGladiator;
            legalities.mHistoric = mHistoric;
            legalities.mHistoricbrawl = mHistoricbrawl;
            legalities.mLegacy = mLegacy;
            legalities.mModern = mModern;
            legalities.mOldschool = mOldschool;
            legalities.mPauper = mPauper;
            legalities.mPaupercommander = mPaupercommander;
            legalities.mPenny = mPenny;
            legalities.mPioneer = mPioneer;
            legalities.mPremodern = mPremodern;
            legalities.mStandard = mStandard;
            legalities.mVintage = mVintage;
            return legalities;
        }

    }

}
