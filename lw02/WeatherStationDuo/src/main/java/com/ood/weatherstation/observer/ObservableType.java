package com.ood.weatherstation.observer;

import java.util.HashMap;
import java.util.Map;

public enum ObservableType {
    IN("in"),
    OUT("out"),
    ERROR("error");

    private final String value;

    ObservableType(String value) {
        this.value = value;
    }

    public ObservableType stringToObservableType(String value) {
        Map<String, ObservableType> observableTypeMap = new HashMap<>();
        observableTypeMap.put(IN.value, IN);
        observableTypeMap.put(OUT.value, OUT);
        return observableTypeMap.get(value.toLowerCase());
    }

}
