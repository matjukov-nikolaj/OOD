package com.ood.coffee.beverage;

import java.util.HashMap;
import java.util.Map;

public enum TeaSort {

    BLACK,
    RED,
    GREEN,
    WHITE;

    private static Map<TeaSort, String> typeToString = getTypeToStringMap();

    private static Map<TeaSort, String> getTypeToStringMap() {
        Map<TeaSort, String> typeToString = new HashMap<>();
        typeToString.put(BLACK, "Black tea");
        typeToString.put(GREEN, "Green tea");
        typeToString.put(RED, "Red tea");
        typeToString.put(WHITE, "White tea");
        return typeToString;
    }

    public static String toString(TeaSort type) {
        return typeToString.get(type);
    }


}
