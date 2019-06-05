package com.ood.coffee.beverage;

import java.util.HashMap;
import java.util.Map;

public enum MilkshakeType {
    SMALL,
    MEDIUM,
    BIG;

    private static Map<MilkshakeType, String> typeToString = getTypeToStringMap();

    private static Map<MilkshakeType, String> getTypeToStringMap() {
        Map<MilkshakeType, String> typeToString = new HashMap<>();
        typeToString.put(SMALL, "Small ");
        typeToString.put(MEDIUM, "Medium ");
        typeToString.put(BIG, "Big ");
        return typeToString;
    }

    public static String toString(MilkshakeType type) {
        return typeToString.get(type);
    }

    private static Map<MilkshakeType, Integer> costsOfTypes = getCostsOfTypeMap();

    private static Map<MilkshakeType, Integer> getCostsOfTypeMap() {
        HashMap<MilkshakeType, Integer> costsOfTypes = new HashMap<>();
        costsOfTypes.put(SMALL, 50);
        costsOfTypes.put(MEDIUM, 60);
        costsOfTypes.put(BIG, 80);
        return costsOfTypes;
    }

    public static int getCostOfType(MilkshakeType type) {
        return costsOfTypes.get(type);
    }

}
