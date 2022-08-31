package com.rezatron.mtgprice.magic.scryfall;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated( "net.hexar.json2pojo" )
@SuppressWarnings( "unused" )
public
class ScryfallCard {

    @SerializedName( "artist" )
    private String mArtist;
    @SerializedName( "artist_ids" )
    private List<String> mArtistIds;
    @SerializedName( "booster" )
    private Boolean mBooster;
    @SerializedName( "border_color" )
    private String mBorderColor;
    @SerializedName( "card_back_id" )
    private String mCardBackId;
    @SerializedName( "cardmarket_id" )
    private Long mCardmarketId;
    @SerializedName( "cmc" )
    private String mCmc;
    @SerializedName( "collector_number" )
    private String mCollectorNumber;
    @SerializedName( "color_identity" )
    private List<String> mColorIdentity;
    @SerializedName( "colors" )
    private List<String> mColors;
    @SerializedName( "digital" )
    private Boolean mDigital;
    @SerializedName( "edhrec_rank" )
    private Long mEdhrecRank;
    @SerializedName( "finishes" )
    private List<String> mFinishes;
    @SerializedName( "flavor_text" )
    private String mFlavorText;
    @SerializedName( "foil" )
    private Boolean mFoil;
    @SerializedName( "frame" )
    private String mFrame;
    @SerializedName( "full_art" )
    private Boolean mFullArt;
    @SerializedName( "games" )
    private List<String> mGames;
    @SerializedName( "highres_image" )
    private Boolean mHighresImage;
    @SerializedName( "id" )
    private String mId;
    @SerializedName( "illustration_id" )
    private String mIllustrationId;
    @SerializedName( "image_status" )
    private String mImageStatus;
    @SerializedName( "image_uris" )
    private ImageUris mImageUris;
    @SerializedName( "keywords" )
    private List<Object> mKeywords;
    @SerializedName( "lang" )
    private String mLangauage;
    @SerializedName( "layout" )
    private String mLayout;
    @SerializedName( "legalities" )
    private Legalities mLegalities;
    @SerializedName( "mana_cost" )
    private String mManaCost;
    @SerializedName( "mtgo_foil_id" )
    private Long mMtgoFoilId;
    @SerializedName( "mtgo_id" )
    private Long mMtgoId;
    @SerializedName( "multiverse_ids" )
    private List<Long> mMultiverseIds;
    @SerializedName( "name" )
    private String mName;
    @SerializedName( "nonfoil" )
    private Boolean mNonfoil;
    @SerializedName( "object" )
    private String mObject;
    @SerializedName( "oracle_id" )
    private String mOracleId;
    @SerializedName( "oracle_text" )
    private String mOracleText;
    @SerializedName( "oversized" )
    private Boolean mOversized;
    @SerializedName( "penny_rank" )
    private Long mPennyRank;
    @SerializedName( "power" )
    private String mPower;
    @SerializedName( "prices" )
    private Prices mPrices;
    @SerializedName( "prints_search_uri" )
    private String mPrintsSearchUri;
    @SerializedName( "promo" )
    private Boolean mPromo;
    @SerializedName( "rarity" )
    private String mRarity;
    @SerializedName( "related_uris" )
    private RelatedUris mRelatedUris;
    @SerializedName( "released_at" )
    private String mReleasedAt;
    @SerializedName( "reprint" )
    private Boolean mReprint;
    @SerializedName( "reserved" )
    private Boolean mReserved;
    @SerializedName( "rulings_uri" )
    private String mRulingsUri;
    @SerializedName( "scryfall_set_uri" )
    private String mScryfallSetUri;
    @SerializedName( "scryfall_uri" )
    private String mScryfallUri;
    @SerializedName( "set" )
    private String mSet;
    @SerializedName( "set_id" )
    private String mSetId;
    @SerializedName( "set_name" )
    private String mSetName;
    @SerializedName( "set_search_uri" )
    private String mSetSearchUri;
    @SerializedName( "set_type" )
    private String mSetType;
    @SerializedName( "set_uri" )
    private String mSetUri;
    @SerializedName( "story_spotlight" )
    private Boolean mStorySpotlight;
    @SerializedName( "tcgplayer_id" )
    private Long mTcgplayerId;
    @SerializedName( "textless" )
    private Boolean mTextless;
    @SerializedName( "toughness" )
    private String mToughness;
    @SerializedName( "type_line" )
    private String mTypeLine;
    @SerializedName( "uri" )
    private String mUri;
    @SerializedName( "variation" )
    private Boolean mVariation;

    @SerializedName( "card_faces" )
    private List<ScryfallCardFace> cardFaces;

    public
    String getArtist() {
        return mArtist;
    }

    public
    List<String> getArtistIds() {
        return mArtistIds;
    }

    public
    Boolean getBooster() {
        return mBooster;
    }

    public
    String getBorderColor() {
        return mBorderColor;
    }

    public
    String getCardBackId() {
        return mCardBackId;
    }

    public
    Long getCardmarketId() {
        return mCardmarketId;
    }

    public
    String getCmc() {
        return mCmc;
    }

    public
    String getCollectorNumber() {
        return mCollectorNumber;
    }

    public
    List<String> getColorIdentity() {
        return mColorIdentity;
    }

    public
    List<String> getColors() {
        return mColors;
    }

    public
    Boolean getDigital() {
        return mDigital;
    }

    public
    Long getEdhrecRank() {
        return mEdhrecRank;
    }

    public
    List<String> getFinishes() {
        return mFinishes;
    }

    public
    String getFlavorText() {
        return mFlavorText;
    }

    public
    Boolean getFoil() {
        return mFoil;
    }

    public
    String getFrame() {
        return mFrame;
    }

    public
    Boolean getFullArt() {
        return mFullArt;
    }

    public
    List<String> getGames() {
        return mGames;
    }

    public
    Boolean getHighresImage() {
        return mHighresImage;
    }

    public
    String getId() {
        return mId;
    }

    public
    String getIllustrationId() {
        return mIllustrationId;
    }

    public
    String getImageStatus() {
        return mImageStatus;
    }

    public
    ImageUris getImageUris() {
        return mImageUris;
    }

    public
    List<Object> getKeywords() {
        return mKeywords;
    }

    public
    String getLangauage() {
        return mLangauage;
    }

    public
    String getLayout() {
        return mLayout;
    }

    public
    Legalities getLegalities() {
        return mLegalities;
    }

    public
    String getManaCost() {
        return mManaCost;
    }

    public
    Long getMtgoFoilId() {
        return mMtgoFoilId;
    }

    public
    Long getMtgoId() {
        return mMtgoId;
    }

    public
    List<Long> getMultiverseIds() {
        return mMultiverseIds;
    }

    public
    String getName() {
        return mName;
    }

    public
    Boolean getNonfoil() {
        return mNonfoil;
    }

    public
    String getObject() {
        return mObject;
    }

    public
    String getOracleId() {
        return mOracleId;
    }

    public
    String getOracleText() {
        return mOracleText;
    }

    public
    Boolean getOversized() {
        return mOversized;
    }

    public
    Long getPennyRank() {
        return mPennyRank;
    }

    public
    String getPower() {
        return mPower;
    }

    public
    Prices getPrices() {
        return mPrices;
    }

    public
    String getPrintsSearchUri() {
        return mPrintsSearchUri;
    }

    public
    Boolean getPromo() {
        return mPromo;
    }

    public
    String getRarity() {
        return mRarity;
    }

    public
    RelatedUris getRelatedUris() {
        return mRelatedUris;
    }

    public
    String getReleasedAt() {
        return mReleasedAt;
    }

    public
    Boolean getReprint() {
        return mReprint;
    }

    public
    Boolean getReserved() {
        return mReserved;
    }

    public
    String getRulingsUri() {
        return mRulingsUri;
    }

    public
    String getScryfallSetUri() {
        return mScryfallSetUri;
    }

    public
    String getScryfallUri() {
        return mScryfallUri;
    }

    public
    String getSet() {
        return mSet;
    }

    public
    String getSetId() {
        return mSetId;
    }

    public
    String getSetName() {
        return mSetName;
    }

    public
    String getSetSearchUri() {
        return mSetSearchUri;
    }

    public
    String getSetType() {
        return mSetType;
    }

    public
    String getSetUri() {
        return mSetUri;
    }

    public
    Boolean getStorySpotlight() {
        return mStorySpotlight;
    }

    public
    Long getTcgplayerId() {
        return mTcgplayerId;
    }

    public
    Boolean getTextless() {
        return mTextless;
    }

    public
    String getToughness() {
        return mToughness;
    }

    public
    String getTypeLine() {
        return mTypeLine;
    }

    public
    String getUri() {
        return mUri;
    }

    public
    Boolean getVariation() {
        return mVariation;
    }

    public
    List<ScryfallCardFace> getCardFaces() {
        return cardFaces;
    }

    public
    void setCardFaces(List<ScryfallCardFace> cardFaces) {
        this.cardFaces = cardFaces;
    }

    public static
    class Builder {

        private String mArtist;
        private List<String> mArtistIds;
        private Boolean mBooster;
        private String mBorderColor;
        private String mCardBackId;
        private Long mCardmarketId;
        private String mCmc;
        private String mCollectorNumber;
        private List<String> mColorIdentity;
        private List<String> mColors;
        private Boolean mDigital;
        private Long mEdhrecRank;
        private List<String> mFinishes;
        private String mFlavorText;
        private Boolean mFoil;
        private String mFrame;
        private Boolean mFullArt;
        private List<String> mGames;
        private Boolean mHighresImage;
        private String mId;
        private String mIllustrationId;
        private String mImageStatus;
        private ImageUris mImageUris;
        private List<Object> mKeywords;
        private String mLangauage;
        private String mLayout;
        private Legalities mLegalities;
        private String mManaCost;
        private Long mMtgoFoilId;
        private Long mMtgoId;
        private List<Long> mMultiverseIds;
        private String mName;
        private Boolean mNonfoil;
        private String mObject;
        private String mOracleId;
        private String mOracleText;
        private Boolean mOversized;
        private Long mPennyRank;
        private String mPower;
        private Prices mPrices;
        private String mPrintsSearchUri;
        private Boolean mPromo;
        private String mRarity;
        private RelatedUris mRelatedUris;
        private String mReleasedAt;
        private Boolean mReprint;
        private Boolean mReserved;
        private String mRulingsUri;
        private String mScryfallSetUri;
        private String mScryfallUri;
        private String mSet;
        private String mSetId;
        private String mSetName;
        private String mSetSearchUri;
        private String mSetType;
        private String mSetUri;
        private Boolean mStorySpotlight;
        private Long mTcgplayerId;
        private Boolean mTextless;
        private String mToughness;
        private String mTypeLine;
        private String mUri;
        private Boolean mVariation;

        private List<ScryfallCardFace> mCardFaces;

        public
        ScryfallCard.Builder withArtist(String artist) {
            mArtist = artist;
            return this;
        }

        public
        ScryfallCard.Builder withArtistIds(List<String> artistIds) {
            mArtistIds = artistIds;
            return this;
        }

        public
        ScryfallCard.Builder withBooster(Boolean booster) {
            mBooster = booster;
            return this;
        }

        public
        ScryfallCard.Builder withBorderColor(String borderColor) {
            mBorderColor = borderColor;
            return this;
        }

        public
        ScryfallCard.Builder withCardBackId(String cardBackId) {
            mCardBackId = cardBackId;
            return this;
        }

        public
        ScryfallCard.Builder withCardmarketId(Long cardmarketId) {
            mCardmarketId = cardmarketId;
            return this;
        }

        public
        ScryfallCard.Builder withCmc(String cmc) {
            mCmc = cmc;
            return this;
        }

        public
        ScryfallCard.Builder withCollectorNumber(String collectorNumber) {
            mCollectorNumber = collectorNumber;
            return this;
        }

        public
        ScryfallCard.Builder withColorIdentity(List<String> colorIdentity) {
            mColorIdentity = colorIdentity;
            return this;
        }

        public
        ScryfallCard.Builder withColors(List<String> colors) {
            mColors = colors;
            return this;
        }

        public
        ScryfallCard.Builder withDigital(Boolean digital) {
            mDigital = digital;
            return this;
        }

        public
        ScryfallCard.Builder withEdhrecRank(Long edhrecRank) {
            mEdhrecRank = edhrecRank;
            return this;
        }

        public
        ScryfallCard.Builder withFinishes(List<String> finishes) {
            mFinishes = finishes;
            return this;
        }

        public
        ScryfallCard.Builder withFlavorText(String flavorText) {
            mFlavorText = flavorText;
            return this;
        }

        public
        ScryfallCard.Builder withFoil(Boolean foil) {
            mFoil = foil;
            return this;
        }

        public
        ScryfallCard.Builder withFrame(String frame) {
            mFrame = frame;
            return this;
        }

        public
        ScryfallCard.Builder withFullArt(Boolean fullArt) {
            mFullArt = fullArt;
            return this;
        }

        public
        ScryfallCard.Builder withGames(List<String> games) {
            mGames = games;
            return this;
        }

        public
        ScryfallCard.Builder withHighresImage(Boolean highresImage) {
            mHighresImage = highresImage;
            return this;
        }

        public
        ScryfallCard.Builder withId(String id) {
            mId = id;
            return this;
        }

        public
        ScryfallCard.Builder withIllustrationId(String illustrationId) {
            mIllustrationId = illustrationId;
            return this;
        }

        public
        ScryfallCard.Builder withImageStatus(String imageStatus) {
            mImageStatus = imageStatus;
            return this;
        }

        public
        ScryfallCard.Builder withImageUris(ImageUris imageUris) {
            mImageUris = imageUris;
            return this;
        }

        public
        ScryfallCard.Builder withKeywords(List<Object> keywords) {
            mKeywords = keywords;
            return this;
        }

        public
        ScryfallCard.Builder withLang(String lang) {
            mLangauage = lang;
            return this;
        }

        public
        ScryfallCard.Builder withLayout(String layout) {
            mLayout = layout;
            return this;
        }

        public
        ScryfallCard.Builder withLegalities(Legalities legalities) {
            mLegalities = legalities;
            return this;
        }

        public
        ScryfallCard.Builder withManaCost(String manaCost) {
            mManaCost = manaCost;
            return this;
        }

        public
        ScryfallCard.Builder withMtgoFoilId(Long mtgoFoilId) {
            mMtgoFoilId = mtgoFoilId;
            return this;
        }

        public
        ScryfallCard.Builder withMtgoId(Long mtgoId) {
            mMtgoId = mtgoId;
            return this;
        }

        public
        ScryfallCard.Builder withMultiverseIds(List<Long> multiverseIds) {
            mMultiverseIds = multiverseIds;
            return this;
        }

        public
        ScryfallCard.Builder withName(String name) {
            mName = name;
            return this;
        }

        public
        ScryfallCard.Builder withNonfoil(Boolean nonfoil) {
            mNonfoil = nonfoil;
            return this;
        }

        public
        ScryfallCard.Builder withObject(String object) {
            mObject = object;
            return this;
        }

        public
        ScryfallCard.Builder withOracleId(String oracleId) {
            mOracleId = oracleId;
            return this;
        }

        public
        ScryfallCard.Builder withOracleText(String oracleText) {
            mOracleText = oracleText;
            return this;
        }

        public
        ScryfallCard.Builder withOversized(Boolean oversized) {
            mOversized = oversized;
            return this;
        }

        public
        ScryfallCard.Builder withPennyRank(Long pennyRank) {
            mPennyRank = pennyRank;
            return this;
        }

        public
        ScryfallCard.Builder withPower(String power) {
            mPower = power;
            return this;
        }

        public
        ScryfallCard.Builder withPrices(Prices prices) {
            mPrices = prices;
            return this;
        }

        public
        ScryfallCard.Builder withPrintsSearchUri(String printsSearchUri) {
            mPrintsSearchUri = printsSearchUri;
            return this;
        }

        public
        ScryfallCard.Builder withPromo(Boolean promo) {
            mPromo = promo;
            return this;
        }

        public
        ScryfallCard.Builder withRarity(String rarity) {
            mRarity = rarity;
            return this;
        }

        public
        ScryfallCard.Builder withRelatedUris(RelatedUris relatedUris) {
            mRelatedUris = relatedUris;
            return this;
        }

        public
        ScryfallCard.Builder withReleasedAt(String releasedAt) {
            mReleasedAt = releasedAt;
            return this;
        }

        public
        ScryfallCard.Builder withReprint(Boolean reprint) {
            mReprint = reprint;
            return this;
        }

        public
        ScryfallCard.Builder withReserved(Boolean reserved) {
            mReserved = reserved;
            return this;
        }

        public
        ScryfallCard.Builder withRulingsUri(String rulingsUri) {
            mRulingsUri = rulingsUri;
            return this;
        }

        public
        ScryfallCard.Builder withScryfallSetUri(String scryfallSetUri) {
            mScryfallSetUri = scryfallSetUri;
            return this;
        }

        public
        ScryfallCard.Builder withScryfallUri(String scryfallUri) {
            mScryfallUri = scryfallUri;
            return this;
        }

        public
        ScryfallCard.Builder withSet(String set) {
            mSet = set;
            return this;
        }

        public
        ScryfallCard.Builder withSetId(String setId) {
            mSetId = setId;
            return this;
        }

        public
        ScryfallCard.Builder withSetName(String setName) {
            mSetName = setName;
            return this;
        }

        public
        ScryfallCard.Builder withSetSearchUri(String setSearchUri) {
            mSetSearchUri = setSearchUri;
            return this;
        }

        public
        ScryfallCard.Builder withSetType(String setType) {
            mSetType = setType;
            return this;
        }

        public
        ScryfallCard.Builder withSetUri(String setUri) {
            mSetUri = setUri;
            return this;
        }

        public
        ScryfallCard.Builder withStorySpotlight(Boolean storySpotlight) {
            mStorySpotlight = storySpotlight;
            return this;
        }

        public
        ScryfallCard.Builder withTcgplayerId(Long tcgplayerId) {
            mTcgplayerId = tcgplayerId;
            return this;
        }

        public
        ScryfallCard.Builder withTextless(Boolean textless) {
            mTextless = textless;
            return this;
        }

        public
        ScryfallCard.Builder withToughness(String toughness) {
            mToughness = toughness;
            return this;
        }

        public
        ScryfallCard.Builder withTypeLine(String typeLine) {
            mTypeLine = typeLine;
            return this;
        }

        public
        ScryfallCard.Builder withUri(String uri) {
            mUri = uri;
            return this;
        }

        public
        ScryfallCard.Builder withVariation(Boolean variation) {
            mVariation = variation;
            return this;
        }

        public
        ScryfallCard.Builder withCardFaces(List<ScryfallCardFace> mCardFaces) {
            mCardFaces = mCardFaces;
            return this;
        }


        public
        ScryfallCard build() {
            ScryfallCard scryfallCard = new ScryfallCard();
            scryfallCard.mArtist = mArtist;
            scryfallCard.mArtistIds = mArtistIds;
            scryfallCard.mBooster = mBooster;
            scryfallCard.mBorderColor = mBorderColor;
            scryfallCard.mCardBackId = mCardBackId;
            scryfallCard.mCardmarketId = mCardmarketId;
            scryfallCard.mCmc = mCmc;
            scryfallCard.mCollectorNumber = mCollectorNumber;
            scryfallCard.mColorIdentity = mColorIdentity;
            scryfallCard.mColors = mColors;
            scryfallCard.mDigital = mDigital;
            scryfallCard.mEdhrecRank = mEdhrecRank;
            scryfallCard.mFinishes = mFinishes;
            scryfallCard.mFlavorText = mFlavorText;
            scryfallCard.mFoil = mFoil;
            scryfallCard.mFrame = mFrame;
            scryfallCard.mFullArt = mFullArt;
            scryfallCard.mGames = mGames;
            scryfallCard.mHighresImage = mHighresImage;
            scryfallCard.mId = mId;
            scryfallCard.mIllustrationId = mIllustrationId;
            scryfallCard.mImageStatus = mImageStatus;
            scryfallCard.mImageUris = mImageUris;
            scryfallCard.mKeywords = mKeywords;
            scryfallCard.mLangauage = mLangauage;
            scryfallCard.mLayout = mLayout;
            scryfallCard.mLegalities = mLegalities;
            scryfallCard.mManaCost = mManaCost;
            scryfallCard.mMtgoFoilId = mMtgoFoilId;
            scryfallCard.mMtgoId = mMtgoId;
            scryfallCard.mMultiverseIds = mMultiverseIds;
            scryfallCard.mName = mName;
            scryfallCard.mNonfoil = mNonfoil;
            scryfallCard.mObject = mObject;
            scryfallCard.mOracleId = mOracleId;
            scryfallCard.mOracleText = mOracleText;
            scryfallCard.mOversized = mOversized;
            scryfallCard.mPennyRank = mPennyRank;
            scryfallCard.mPower = mPower;
            scryfallCard.mPrices = mPrices;
            scryfallCard.mPrintsSearchUri = mPrintsSearchUri;
            scryfallCard.mPromo = mPromo;
            scryfallCard.mRarity = mRarity;
            scryfallCard.mRelatedUris = mRelatedUris;
            scryfallCard.mReleasedAt = mReleasedAt;
            scryfallCard.mReprint = mReprint;
            scryfallCard.mReserved = mReserved;
            scryfallCard.mRulingsUri = mRulingsUri;
            scryfallCard.mScryfallSetUri = mScryfallSetUri;
            scryfallCard.mScryfallUri = mScryfallUri;
            scryfallCard.mSet = mSet;
            scryfallCard.mSetId = mSetId;
            scryfallCard.mSetName = mSetName;
            scryfallCard.mSetSearchUri = mSetSearchUri;
            scryfallCard.mSetType = mSetType;
            scryfallCard.mSetUri = mSetUri;
            scryfallCard.mStorySpotlight = mStorySpotlight;
            scryfallCard.mTcgplayerId = mTcgplayerId;
            scryfallCard.mTextless = mTextless;
            scryfallCard.mToughness = mToughness;
            scryfallCard.mTypeLine = mTypeLine;
            scryfallCard.mUri = mUri;
            scryfallCard.mVariation = mVariation;
            scryfallCard.cardFaces = mCardFaces;
            return scryfallCard;
        }

    }

}
