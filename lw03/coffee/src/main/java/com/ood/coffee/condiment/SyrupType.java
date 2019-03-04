package com.ood.coffee.condiment;

import java.util.HashMap;
import java.util.Map;

public enum SyrupType {

    CHOCOLATE,
    MAPLE;

    private static Map<SyrupType, String> typeToString = getTypeToStringMap();

    private static Map<SyrupType, String> getTypeToStringMap() {
        HashMap<SyrupType, String> typeToString = new HashMap<>();
        typeToString.put(CHOCOLATE, "Chocolate");
        typeToString.put(MAPLE, "Maple");
        return typeToString;
    }

    public static String toString(SyrupType type) {
        return typeToString.get(type);
    }

}
