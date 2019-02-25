package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.function.Function;
import com.ood.weatherstation.observer.ObservableType;

public class ObservableTypeAction {

    public static void actionHandler(ObservableType type, Function inAction, Function outAction) throws IncorrectObservableType {
        switch (type) {
            case IN:
                inAction.perform();
                break;
            case OUT:
                outAction.perform();
                break;
            default:
                throw new IncorrectObservableType("Incorrect observable type");
        }
    }

}
