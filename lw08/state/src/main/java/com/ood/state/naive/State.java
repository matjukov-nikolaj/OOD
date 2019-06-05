package com.ood.state.naive;

import java.util.HashMap;
import java.util.Map;

public enum State {

    SOLD_OUT,
    NO_QUARTER,
    HAS_QUARTER,
    SOLD;

    private static Map<State, String> typeToString = getTypeToStringMap();

    private static Map<State, String> getTypeToStringMap() {
        HashMap<State, String> typeToString = new HashMap<>();
        typeToString.put(SOLD_OUT, "sold out");
        typeToString.put(NO_QUARTER, "waiting for quarter");
        typeToString.put(HAS_QUARTER, "waiting for turn of crank");
        typeToString.put(SOLD, "delivering a gumball");
        return typeToString;
    }

    public static String toString(State type) {
        return typeToString.get(type);
    }

}
