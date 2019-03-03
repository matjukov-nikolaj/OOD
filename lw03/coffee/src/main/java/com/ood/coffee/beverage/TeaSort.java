package com.ood.coffee.beverage;

import java.util.HashMap;

public enum TeaSort {

    BLACK,
    RED,
    GREEN,
    WHITE;

    @Override
    public String toString() {
        HashMap<TeaSort, String> typeToString = new HashMap<>();
        typeToString.put(BLACK, "Black tea");
        typeToString.put(GREEN, "Green tea");
        typeToString.put(RED, "Red tea");
        typeToString.put(WHITE, "White tea");
        return typeToString.get(this);
    }

}
