package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observable;
import com.ood.weatherstation.observer.ObservableType;
import com.ood.weatherstation.observer.Observer;

public interface BasicDisplay<T> extends Observer<T> {

    void registerInsideObservable(Observable<T> observable);
    void registerOutsideObservable(Observable<T> observable);
    void unregisterObservable(Observable<T> observable);
    ObservableType getType(Observable<T> observable);

}
