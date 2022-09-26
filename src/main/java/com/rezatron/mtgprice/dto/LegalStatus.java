package com.rezatron.mtgprice.dto;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public
enum LegalStatus {
    LEGAL( "legal" ),
    NOT_LEGAL( "not_legal" ),
    RESTRICTED( "restricted" ),
    BANNED( "banned" );

    private static final Map<String, LegalStatus> lookup = new HashMap<>();

    static {
        for (LegalStatus ls : LegalStatus.values()) {
            lookup.put( ls.getLabel(),
                        ls );
        }
    }

    private final String label;

    LegalStatus(String label)
    {
        this.label = label;
    }

    public static
    LegalStatus getFromLabel(String label) {
        if (label == null || label.isBlank() || label.isEmpty()) return null;
        return lookup.get( label.toLowerCase() );
    }

    public
    String getLabel() {
        return label;
    }
}
