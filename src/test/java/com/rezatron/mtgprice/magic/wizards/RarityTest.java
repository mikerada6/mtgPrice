package com.rezatron.mtgprice.magic.wizards;

import com.rezatron.mtgprice.dto.magic.wizards.Rarity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RarityTest {

    @Test
    void fromShortName() {
        assertEquals( Rarity.COMMON,
                      Rarity.fromShortName( "common" ) );
        assertEquals( Rarity.COMMON,
                      Rarity.fromShortName( "COMMON" ) );
        assertEquals( Rarity.COMMON,
                      Rarity.fromShortName( "Common" ) );

        assertEquals( Rarity.UNCOMMON,
                      Rarity.fromShortName( "uncommon" ) );
        assertEquals( Rarity.UNCOMMON,
                      Rarity.fromShortName( "UNCOMMON" ) );
        assertEquals( Rarity.UNCOMMON,
                      Rarity.fromShortName( "Uncommon" ) );

        assertEquals( Rarity.RARE,
                      Rarity.fromShortName( "rare" ) );
        assertEquals( Rarity.RARE,
                      Rarity.fromShortName( "RARE" ) );
        assertEquals( Rarity.RARE,
                      Rarity.fromShortName( "RARE" ) );

        assertEquals( Rarity.MYTHIC,
                      Rarity.fromShortName( "mythic" ) );
        assertEquals( Rarity.MYTHIC,
                      Rarity.fromShortName( "MYTHIC" ) );
        assertEquals( Rarity.MYTHIC,
                      Rarity.fromShortName( "Mythic" ) );

        assertEquals( Rarity.SPECIAL,
                      Rarity.fromShortName( "special" ) );
        assertEquals( Rarity.SPECIAL,
                      Rarity.fromShortName( "SPECIAL" ) );
        assertEquals( Rarity.SPECIAL,
                      Rarity.fromShortName( "Special" ) );

        assertEquals( Rarity.BONUS,
                      Rarity.fromShortName( "bonus" ) );
        assertEquals( Rarity.BONUS,
                      Rarity.fromShortName( "BONUS" ) );
        assertEquals( Rarity.BONUS,
                      Rarity.fromShortName( "Bonus" ) );

        Exception exception = assertThrows( IllegalArgumentException.class,
                                            () -> Rarity.fromShortName( "Cool" ) );
        String expectedMessage = "ShortName [Cool] not supported.";
        String actualMessage = exception.getMessage();
        assertTrue( actualMessage.contains( expectedMessage ) );

        exception = assertThrows( IllegalArgumentException.class,
                                  () -> Rarity.fromShortName( "cool" ) );
        expectedMessage = "ShortName [cool] not supported.";
        actualMessage = exception.getMessage();
        assertTrue( actualMessage.contains( expectedMessage ) );

        exception = assertThrows( IllegalArgumentException.class,
                                  () -> Rarity.fromShortName( "" ) );
        expectedMessage = "ShortName [] not supported.";
        actualMessage = exception.getMessage();
        assertTrue( actualMessage.contains( expectedMessage ) );
    }

    @Test
    void getShortName() {
        assertEquals( "common",
                      Rarity.COMMON.getShortName() );
        assertEquals( "uncommon",
                      Rarity.UNCOMMON.getShortName() );
        assertEquals( "rare",
                      Rarity.RARE.getShortName() );
        assertEquals( "mythic",
                      Rarity.MYTHIC.getShortName() );
        assertEquals( "bonus",
                      Rarity.BONUS.getShortName() );
        assertEquals( "special",
                      Rarity.SPECIAL.getShortName() );

        assertEquals( "unknown",
                      Rarity.UNKNOWN.getShortName() );
    }
}
