package com.rezatron.mtgprice.dto.magic.wizards;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public
enum Color {
    WHITE( "W" ),
    BLUE( "U" ),
    BLACK( "B" ),
    RED( "R" ),
    GREEN( "G" );

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<String, Color> lookup = new HashMap<>();

    static {
        for (Color c : Color.values()) {
            lookup.put( c.getLabel(),
                        c );
        }
    }

    private final String label;


    Color(String label)
    {
        this.label = label;
    }

    public static
    Color getFromLabel(String label) {
        if (label == null || label.isBlank() || label.isEmpty()) return null;
        return lookup.get( label.toUpperCase() );
    }

    public
    String getLabel() {
        return label;
    }

}
