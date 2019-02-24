package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.Observable;

public class SimpleObserver extends BasicDisplayImpl<WeatherInfo> {

    @Override
    public void update(WeatherInfo data, Observable<WeatherInfo> observable) {
        System.out.println("I'm a simple observer.");
        try {
            switch (this.getType(observable)) {
                case IN:
                    System.out.println("Inside");
                    break;
                case OUT:
                    System.out.println("Outside");
                    break;
                default:
                    break;
            }
        } catch (IncorrectObservableType e) {
            System.out.println(e.getMessage());
        }
    }

}
