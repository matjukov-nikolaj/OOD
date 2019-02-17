package com.ood.weatherstation.observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ObservableImpl<T> implements Observable<T> {

    Set<Observer<T>> observers;
    List<Observer<T>> remoteObservers;

    public ObservableImpl() {
        this.observers = new HashSet<>();
        this.remoteObservers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer<T> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver() {
        T data = this.getChangedData();
        Set<Observer<T>> tempObservers = new HashSet<>(this.observers);
        for (Observer observer : tempObservers) {
            observer.update(data);
        }
        for (Observer observer : this.remoteObservers) {
            this.observers.remove(observer);
        }
        this.remoteObservers.clear();
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
        this.remoteObservers.add(observer);
    }

    protected abstract T getChangedData();

}
