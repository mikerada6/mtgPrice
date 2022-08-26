package com.rezatron.mtgprice.magic.wizards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColorTest {

    @Test
    void getFromLabel() {

        assertEquals(Color.WHITE, Color.getFromLabel("w"));
        assertEquals(Color.WHITE, Color.getFromLabel("W"));

        assertEquals(Color.BLUE, Color.getFromLabel("u"));
        assertEquals(Color.BLUE, Color.getFromLabel("U"));

        assertEquals(Color.BLACK, Color.getFromLabel("b"));
        assertEquals(Color.BLACK, Color.getFromLabel("B"));

        assertEquals(Color.RED, Color.getFromLabel("r"));
        assertEquals(Color.RED, Color.getFromLabel("r"));

        assertEquals(Color.GREEN, Color.getFromLabel("g"));
        assertEquals(Color.GREEN, Color.getFromLabel("G"));

        assertNull(Color.getFromLabel("a"));
        assertNull(Color.getFromLabel("A"));
        assertNull(Color.getFromLabel(""));
        assertNull(Color.getFromLabel(""));
        assertNull(Color.getFromLabel(" "));
        assertNull(Color.getFromLabel(" "));
        assertNull(Color.getFromLabel(null));
        assertNull(Color.getFromLabel(null));
    }

    @Test
    void getLabel() {

        assertEquals("W", Color.WHITE.getLabel());
        assertEquals("U", Color.BLUE.getLabel());
        assertEquals("B", Color.BLACK.getLabel());
        assertEquals("R", Color.RED.getLabel());
        assertEquals("G", Color.GREEN.getLabel());
    }

    @Test
    void values() {
    }

    @Test
    void valueOf() {
    }
}

