package com.ood.weatherstation.observer;

public interface Observable<T> {

    void registerObserver(Observer<T> observer, Integer priority);

    void notifyObserver();

    void removeObserver(Observer<T> observer);

}
