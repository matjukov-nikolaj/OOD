package com.ood.coffee.beverage;

import java.util.HashMap;

public enum CoffeeType {

    STANDARD,
    DOUBLE;

    @Override
    public String toString() {
        HashMap<CoffeeType, String> typeToString = new HashMap<>();
        typeToString.put(STANDARD, "Standard ");
        typeToString.put(DOUBLE, "Double ");
        return typeToString.get(this);
    }

}
