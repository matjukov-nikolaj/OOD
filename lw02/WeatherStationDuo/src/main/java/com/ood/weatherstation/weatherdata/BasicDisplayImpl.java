package com.ood.weatherstation.weatherdata;

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
        if (observable.equals(this.insideObservable) && observable.hashCode() == this.insideObservable.hashCode()) {
            this.insideObservable = null;
        } else if (observable.equals(this.outsideObservable) && observable.hashCode() == this.outsideObservable.hashCode()) {
            this.outsideObservable = null;
        }
    }

    @Override
    public ObservableType getType(Observable<T> observable) {
        if (observable.equals(this.insideObservable) && observable.hashCode() == this.insideObservable.hashCode()) {
            return ObservableType.IN;
        } else if (observable.equals(this.outsideObservable) && observable.hashCode() == this.outsideObservable.hashCode()) {
            return ObservableType.OUT;
        }
        throw new IllegalArgumentException();
    }

}
