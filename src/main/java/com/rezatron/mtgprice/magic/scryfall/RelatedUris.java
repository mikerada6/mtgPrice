
package com.rezatron.mtgprice.magic.scryfall;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RelatedUris {

    @SerializedName("edhrec")
    private String mEdhrec;
    @SerializedName("gatherer")
    private String mGatherer;
    @SerializedName("tcgplayer_infinite_articles")
    private String mTcgplayerInfiniteArticles;
    @SerializedName("tcgplayer_infinite_decks")
    private String mTcgplayerInfiniteDecks;

    public String getEdhrec() {
        return mEdhrec;
    }

    public String getGatherer() {
        return mGatherer;
    }

    public String getTcgplayerInfiniteArticles() {
        return mTcgplayerInfiniteArticles;
    }

    public String getTcgplayerInfiniteDecks() {
        return mTcgplayerInfiniteDecks;
    }

    public static class Builder {

        private String mEdhrec;
        private String mGatherer;
        private String mTcgplayerInfiniteArticles;
        private String mTcgplayerInfiniteDecks;

        public RelatedUris.Builder withEdhrec(String edhrec) {
            mEdhrec = edhrec;
            return this;
        }

        public RelatedUris.Builder withGatherer(String gatherer) {
            mGatherer = gatherer;
            return this;
        }

        public RelatedUris.Builder withTcgplayerInfiniteArticles(String tcgplayerInfiniteArticles) {
            mTcgplayerInfiniteArticles = tcgplayerInfiniteArticles;
            return this;
        }

        public RelatedUris.Builder withTcgplayerInfiniteDecks(String tcgplayerInfiniteDecks) {
            mTcgplayerInfiniteDecks = tcgplayerInfiniteDecks;
            return this;
        }

        public RelatedUris build() {
            RelatedUris relatedUris = new RelatedUris();
            relatedUris.mEdhrec = mEdhrec;
            relatedUris.mGatherer = mGatherer;
            relatedUris.mTcgplayerInfiniteArticles = mTcgplayerInfiniteArticles;
            relatedUris.mTcgplayerInfiniteDecks = mTcgplayerInfiniteDecks;
            return relatedUris;
        }

    }

}
