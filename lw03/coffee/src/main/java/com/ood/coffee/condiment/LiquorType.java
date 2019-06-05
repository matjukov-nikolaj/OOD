package com.ood.coffee.condiment;

import java.util.HashMap;
import java.util.Map;

public enum LiquorType {

    CHOCOLATE,
    NUT;


    private static Map<LiquorType, String> typeToString = getTypeToStringMap();

    private static Map<LiquorType, String> getTypeToStringMap() {
        HashMap<LiquorType, String> typeToString = new HashMap<>();
        typeToString.put(CHOCOLATE, "Chocolate ");
        typeToString.put(NUT, "Nut ");
        return typeToString;
    }

    public static String toString(LiquorType type) {
        return typeToString.get(type);
    }


}
