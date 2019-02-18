package com.ood.weatherstation.observer;

import java.util.*;

public abstract class ObservableImpl<T> implements Observable<T> {

    TreeMap<Integer, Observer<T>> observers;

    public ObservableImpl() {
        this.observers = new TreeMap<>();
    }

    @Override
    public void registerObserver(Observer<T> observer, int priority) {
        removeObserver(observer);
        observers.put(priority, observer);
    }

    @Override
    public void notifyObserver() {
        T data = this.getChangedData();
        TreeMap<Integer, Observer<T>> tempObservers = new TreeMap<>(Collections.reverseOrder());
        tempObservers.putAll(this.observers);
        for (Map.Entry<Integer, Observer<T>> entry : tempObservers.entrySet()) {
            Observer<T> observer = entry.getValue();
            observer.update(data);
        }
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        TreeMap<Integer, Observer<T>> newObservers = new TreeMap<>();
        for (Map.Entry<Integer, Observer<T>> entry : this.observers.entrySet()) {
            Observer<T> observerInSet = entry.getValue();
            int priority = entry.getKey();
            if (observerInSet != observer) {
                newObservers.put(priority, observerInSet);
            }
        }
        this.observers.clear();
        this.observers.putAll(newObservers);
    }

    protected abstract T getChangedData();

}
