package com.ood.weatherstation.observer;

import com.ood.weatherstation.exception.IncorrectObservableType;

public interface Observable<T> {

    void registerObserver(Observer<T> observer, int priority) throws IncorrectObservableType;

    void notifyObserver();

    void removeObserver(Observer<T> observer) throws IncorrectObservableType;

}
