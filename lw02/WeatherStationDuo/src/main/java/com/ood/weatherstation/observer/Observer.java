package com.ood.weatherstation.observer;

public interface Observer<T> extends ObserverType {

    void update(T data);

}
