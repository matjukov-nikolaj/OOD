package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.Observable;
import com.ood.weatherstation.observer.Observer;

public class NormalObserver implements Observer<WeatherInfo> {

    private Observable<WeatherInfo> inObservable;

    private Observable<WeatherInfo> outObservable;

    public NormalObserver(Observable<WeatherInfo> inObservable,
                   Observable<WeatherInfo> outObservable) {
        this.inObservable = inObservable;
        this.outObservable = outObservable;
    }

    public void update(Observable<WeatherInfo> observable, WeatherInfo data) throws IncorrectObservableType  {
        if (observable == this.inObservable) {
            System.out.println("Normal Inside.");
        } else if (observable == this.outObservable) {
            System.out.println("Normal Outside.");
        } else {
            throw new IncorrectObservableType("Incorrect observable type.");
        }
    }


}
