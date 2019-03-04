package com.ood.factory.shapefactory;

import com.ood.exceptions.UnknownColorException;

import java.util.HashMap;
import java.util.Map;

public enum Color {

    GREEN,
    RED,
    BLUE,
    YELLOW,
    PINK,
    BLACK;

    private static Map<Color, String> typeToString = getTypeToStringMap();

    private static Map<Color, String> getTypeToStringMap() {
        Map<Color, String> typeToString = new HashMap<>();
        typeToString.put(Color.GREEN, "GREEN");
        typeToString.put(Color.RED, "RED");
        typeToString.put(Color.BLUE, "BLUE");
        typeToString.put(Color.YELLOW, "YELLOW");
        typeToString.put(Color.PINK, "PINK");
        typeToString.put(Color.BLACK, "BLACK");
        return typeToString;
    }

    public static String toString(Color type) {
        return typeToString.get(type);
    }

    public static Map<String, Color> stringToColor = getStringToColorMap();

    private static Map<String, Color> getStringToColorMap() {
        Map<String, Color> stringToColor = new HashMap<>();
        stringToColor.put("green", GREEN);
        stringToColor.put("red", RED);
        stringToColor.put("blue", BLUE);
        stringToColor.put("yellow", YELLOW);
        stringToColor.put("pink", PINK);
        stringToColor.put("black", BLACK);
        return stringToColor;
    }


    public static Color toColor(String mode) throws UnknownColorException {
        Color color = stringToColor.get(mode.toLowerCase());
        if (color == null) {
            throw new UnknownColorException();
        }
        return color;
    }

}
