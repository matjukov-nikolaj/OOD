package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observer;

public class NormalObserver implements Observer<WeatherInfo> {

    @Override
    public void update(WeatherInfo data) {
        System.out.println("I am a normal.");
    }

}
