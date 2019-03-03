package com.ood.coffee.condiment;

import java.util.HashMap;

public enum IceCubeType {

    DRY,
    WATER;

    @Override
    public String toString() {
        HashMap<IceCubeType, String> typeToString = new HashMap<>();
        typeToString.put(DRY, "Dry");
        typeToString.put(WATER, "Water");
        return typeToString.get(this);
    }
}
