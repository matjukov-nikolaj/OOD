package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observable;
import com.ood.weatherstation.observer.Observer;

public class SuicideObserver implements Observer<WeatherInfo> {

    public SuicideObserver(Observable<WeatherInfo> observable) {
        this.observable = observable;
    }

    @Override
    public void update(WeatherInfo data) {
        observable.removeObserver(this);
        System.out.println("I am a suicide.");
    }

    private Observable<WeatherInfo> observable;
}
