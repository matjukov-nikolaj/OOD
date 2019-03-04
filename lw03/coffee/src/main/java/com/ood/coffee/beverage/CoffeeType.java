package com.ood.coffee.beverage;

import java.util.HashMap;
import java.util.Map;

public enum CoffeeType {

    STANDARD,
    DOUBLE;

    private static Map<CoffeeType, String> typeToString = getTypeToStringMap();

    private static Map<CoffeeType, String> getTypeToStringMap() {
        HashMap<CoffeeType, String> typeToString = new HashMap<>();
        typeToString.put(STANDARD, "Standard ");
        typeToString.put(DOUBLE, "Double ");
        return typeToString;
    }

    public static String toString(CoffeeType type) {
        return typeToString.get(type);
    }

}
