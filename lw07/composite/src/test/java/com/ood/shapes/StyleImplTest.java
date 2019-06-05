package com.ood.shapes;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class StyleImplTest {

    @Test
    public void canCreateStyle() {
        Style style = new StyleImpl(false, Color.decode("#000000"));
        assertEquals(Color.BLACK, style.getColor());
    }

    @Test
    public void canCreateEnableStyle() {
        Style style = new StyleImpl(true, Color.decode("#ffffff"));
        assertEquals(Color.WHITE, style.getColor());
        assertTrue(style.isEnabled());
    }

    @Test
    public void canCompareTwoStyles() {
        Style style1 = new StyleImpl(true, Color.decode("#ffffff"));
        Style style2 = new StyleImpl(true, Color.decode("#ffffff"));
        Style style3 = new StyleImpl(true, Color.decode("#000000"));
        Style style4 = new StyleImpl(false, Color.decode("#000000"));
        assertTrue(style1.equals(style2));
        assertFalse(style2.equals(style3));
        assertFalse(style3.equals(style4));
    }

}