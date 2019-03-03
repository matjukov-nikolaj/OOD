package com.ood.coffee.beverage;

import java.util.HashMap;

public enum MilkshakeType {
    SMALL,
    MEDIUM,
    BIG;

    public int getCostOfType() {
        HashMap<MilkshakeType, Integer> costsOfTypes = new HashMap<>();
        costsOfTypes.put(SMALL, 50);
        costsOfTypes.put(MEDIUM, 60);
        costsOfTypes.put(BIG, 80);
        return costsOfTypes.get(this);
    }

    @Override
    public String toString() {
        HashMap<MilkshakeType, String> typeToString = new HashMap<>();
        typeToString.put(SMALL, "Small ");
        typeToString.put(MEDIUM, "Medium ");
        typeToString.put(BIG, "Big ");
        return typeToString.get(this);
    }

}
