package com.rezatron.mtgprice.magic.wizards;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public
enum Rarity {
    COMMON("common"),
    UNCOMMON("uncommon"),
    RARE("rare"),
    SPECIAL("special"),
    MYTHIC("mythic"),
    BONUS("bonus"),
    UNKNOWN( "unknown");


    private final String shortName;

    Rarity(String shortName) {
        this.shortName = shortName;
    }

    public static
    Rarity fromShortName(String shortName) {
        switch (shortName.toLowerCase()) {
            case "common":
                return Rarity.COMMON;
            case "uncommon":
                return Rarity.UNCOMMON;
            case "rare":
                return Rarity.RARE;
            case "special":
                return Rarity.SPECIAL;
            case "mythic":
                return Rarity.MYTHIC;
            case "bonus":
                return Rarity.BONUS;
            default:
                log.error("ShortName {} not supported.",
                          shortName);
                throw new IllegalArgumentException("ShortName [" + shortName + "] not supported.");
        }
    }

    public
    String getShortName() {
        return shortName;
    }

}
