package com.rezatron.mtgprice.dto.magic.wizards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public
enum CardType {
    ARTIFACT( "artifact" ),
    CONSPIRACY( "conspiracy" ),
    CREATURE( "creature" ),
    DUNGEON( "dungeon" ),
    ENCHANTMENT( "enchantment" ),
    INSTANT( "instant" ),
    LAND( "land" ),
    PHENOMENON( "phenomenon" ),
    PLANESWALKER( "planeswalker" ),
    SCHEME( "scheme" ),
    SORCERY( "sorcery" ),
    TRIBAL( "triabal" ),
    VANGUARD( "vanguard" ),
    TOKEN( "token" ),
    BASICLAND( "basicland" ),
    OTHER( "other" );
    private static final Map<String, CardType> lookup = new HashMap<>();

    static {
        for (CardType c : CardType.values()) {
            lookup.put( c.getCardType(),
                        c );
        }
    }

    private final String cardType;

    CardType(String cardType)
    {
        this.cardType = cardType;
    }

    public static
    List<CardType> getCardTypeFromScryFallTypeLine(String label) {
        ArrayList<CardType> cardTypes = new ArrayList<>();
        if (label == null) return cardTypes;
        label = label.toLowerCase();
        if (label.contains( "card" ) || label.contains( "eaturecray" ) || label.contains( "hero" )
            || label.contains( "scheme" ) || label.contains( "phenomenon" ) || label.contains( "plane" )
            || label.contains( "scariest creature you’ll ever see" ) || label.contains( "scheme" ) || label.contains( "summon" ) || label.contains( "vanguard" ))
        {
            cardTypes.add( CardType.OTHER );
            return cardTypes;
        }
        if (label.contains( "token" ) || label.contains( "dungeon" ) || label.contains( "emblem" ) || label.contains( "sticker" )) {
            cardTypes.add( CardType.TOKEN );
            return cardTypes;
        }
        if (label.contains( "basic" )) {
            cardTypes.add( CardType.BASICLAND );
            return cardTypes;
        }
        if (label.contains( "conspiracy" )) {
            cardTypes.add( CardType.CONSPIRACY );
        }
        if (label.contains( "artifact" )) {
            cardTypes.add( CardType.ARTIFACT );
        }
        if (label.contains( "instant" )) {
            cardTypes.add( CardType.INSTANT );
        }
        if (label.contains( "sorcery" )) {
            cardTypes.add( CardType.SORCERY );
        }
        if (label.contains( "land" )) {
            cardTypes.add( CardType.LAND );
        }
        if (label.contains( "enchantment" )) {
            cardTypes.add( CardType.ENCHANTMENT );
        }
        if (label.contains( "planeswalker" )) {
            cardTypes.add( CardType.PLANESWALKER );
        }
        if (label.contains( "tribal" )) {
            cardTypes.add( CardType.TRIBAL );
        }
        if (label.contains( "creature" )) {
            cardTypes.add( CardType.CREATURE );
        }
        return cardTypes;
    }

    public
    String getCardType() {
        return cardType;
    }
}
