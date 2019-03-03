package com.ood.coffee.condiment;

import java.util.HashMap;

public enum SyrupType {

    CHOCOLATE,
    MAPLE;

    @Override
    public String toString() {
        HashMap<SyrupType, String> typeToString = new HashMap<>();
        typeToString.put(CHOCOLATE, "Chocolate");
        typeToString.put(MAPLE, "Maple");
        return typeToString.get(this);
    }

}
