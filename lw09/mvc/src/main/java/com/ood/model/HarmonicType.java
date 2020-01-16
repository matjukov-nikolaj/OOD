package com.ood.model;

import java.util.HashMap;
import java.util.Map;

public enum HarmonicType {

    SIN,
    COS;

    private static Map<HarmonicType, String> typeToString = getTypeToStringMap();

    private static Map<String, HarmonicType> stringToType = getStringToTypeMap();

    private static Map<String, HarmonicType> getStringToTypeMap() {
        Map<String, HarmonicType> stringToType = new HashMap<>();
        stringToType.put("sin", SIN);
        stringToType.put("cos", COS);
        return stringToType;
    }

    private static Map<HarmonicType, String> getTypeToStringMap() {
        Map<HarmonicType, String> typeToString = new HashMap<>();
        typeToString.put(SIN, "sin");
        typeToString.put(COS, "cos");
        return typeToString;
    }

    public static String toString(HarmonicType type) {
        return typeToString.get(type);
    }

    public static HarmonicType toType(String str) {
        return stringToType.get(str.toLowerCase());
    }

}
