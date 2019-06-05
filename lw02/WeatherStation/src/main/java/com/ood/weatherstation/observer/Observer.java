package com.ood.weatherstation.observer;

public interface Observer<T> {

    void update(T data);

}
