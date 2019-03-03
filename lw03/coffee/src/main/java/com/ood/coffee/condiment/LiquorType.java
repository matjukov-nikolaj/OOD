package com.ood.coffee.condiment;

import java.util.HashMap;

public enum LiquorType {

    CHOCOLATE,
    NUT;

    @Override
    public String toString() {
        HashMap<LiquorType, String> typeToString = new HashMap<>();
        typeToString.put(CHOCOLATE, "Chocolate ");
        typeToString.put(NUT, "Nut ");
        return typeToString.get(this);
    }

}
