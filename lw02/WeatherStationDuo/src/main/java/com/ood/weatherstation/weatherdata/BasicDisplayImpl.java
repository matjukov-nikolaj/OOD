package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.Observable;
import com.ood.weatherstation.observer.ObservableType;

public abstract class BasicDisplayImpl<T> implements BasicDisplay<T> {

    private Observable<T> insideObservable;

    private Observable<T> outsideObservable;

    @Override
    public void registerInsideObservable(Observable<T> observable) {
        this.insideObservable = observable;
    }

    @Override
    public void registerOutsideObservable(Observable<T> observable) {
        this.outsideObservable = observable;
    }

    @Override
    public void unregisterObservable(Observable<T> observable) {
        if (observable == this.insideObservable) {
            this.insideObservable = null;
        } else if (observable == this.outsideObservable) {
            this.outsideObservable = null;
        }
    }

    @Override
    public ObservableType getType(Observable<T> observable) throws IncorrectObservableType {
        if (observable == this.insideObservable) {
            return ObservableType.IN;
        } else if (observable == this.outsideObservable) {
            return ObservableType.OUT;
        }
        throw new IncorrectObservableType("Incorrect observable type");
    }

}
