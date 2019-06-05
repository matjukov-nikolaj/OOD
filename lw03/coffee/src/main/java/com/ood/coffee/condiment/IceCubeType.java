package com.ood.coffee.condiment;

import java.util.HashMap;
import java.util.Map;

public enum IceCubeType {

    DRY,
    WATER;

    private static Map<IceCubeType, String> typeToString = getTypeToStringMap();

    private static Map<IceCubeType, String> getTypeToStringMap() {
        HashMap<IceCubeType, String> typeToString = new HashMap<>();
        typeToString.put(DRY, "Dry");
        typeToString.put(WATER, "Water");
        return typeToString;
    }

    public static String toString(IceCubeType type) {
        return typeToString.get(type);
    }

}
