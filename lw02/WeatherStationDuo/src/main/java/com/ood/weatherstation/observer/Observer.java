package com.ood.weatherstation.observer;

import com.ood.weatherstation.exception.IncorrectObservableType;

public interface Observer<T> {

    void update(Observable<T> observable, T data) throws IncorrectObservableType;

}
