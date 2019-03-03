package com.ood.factory;

import java.util.HashMap;

public enum Color {

    GREEN,
    RED,
    BLUE,
    YELLOW,
    PINK,
    BLACK;

    @Override
    public String toString() {
        HashMap<Color, String> typeToString = new HashMap<>();
        typeToString.put(Color.GREEN, "Green");
        typeToString.put(Color.RED, "Red");
        typeToString.put(Color.BLUE, "Blue");
        typeToString.put(Color.YELLOW, "Yellow");
        typeToString.put(Color.PINK, "Pink");
        typeToString.put(Color.BLACK, "Black");
        return typeToString.get(this);
    }

}
