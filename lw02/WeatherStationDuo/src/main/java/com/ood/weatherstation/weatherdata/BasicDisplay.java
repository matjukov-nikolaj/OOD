package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observer;

public interface BasicDisplay<T> {

    void setAsInsideObservable(Observer<T> observable);
    void setAsOutsideObservable(Observer<T> observable);

}
