package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.Observable;
import com.ood.weatherstation.observer.Observer;

public class SimpleObserver implements Observer<WeatherInfo> {

    private Observable<WeatherInfo> inObservable;

    private Observable<WeatherInfo> outObservable;

    public SimpleObserver(Observable<WeatherInfo> inObservable,
                          Observable<WeatherInfo> outObservable) {
        this.inObservable = inObservable;
        this.outObservable = outObservable;
    }

    public void update(Observable<WeatherInfo> observable, WeatherInfo data) throws IncorrectObservableType {
        if (observable == this.inObservable) {
            System.out.println("Simple Inside. I have statistic.");
        } else if (observable == this.outObservable) {
            System.out.println("Simple Outside. I have statistic.");
        } else {
            throw new IncorrectObservableType("Incorrect observable type.");
        }
    }

}
