package com.ood.weatherstation.observer;

import javafx.util.Pair;

import java.util.*;

public abstract class ObservableImpl<T> implements Observable<T> {

    Set<Pair<Integer, Observer<T>>> observers;

    public ObservableImpl() {
        this.observers = new HashSet<>();
    }

    @Override
    public void registerObserver(Observer<T> observer, Integer priority) {
        removeObserver(observer);
        observers.add(new Pair<>(priority, observer));
    }

    @Override
    public void notifyObserver() {
        T data = this.getChangedData();
        List<Pair<Integer, Observer<T>>> tempObservers = new ArrayList<>(this.observers);
        Collections.sort(tempObservers, new Comparator<Pair<Integer, Observer<T>>>() {
            @Override
            public int compare(Pair<Integer, Observer<T>> o1, Pair<Integer, Observer<T>> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });
        for (Pair<Integer, Observer<T>> observerPair : tempObservers) {
            Observer<T> observer = observerPair.getValue();
            observer.update(data);
        }
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        Set<Pair<Integer, Observer<T>>> newObservers = new HashSet<>();
        for (Pair<Integer, Observer<T>> observerPair : this.observers) {
            Observer<T> observerInSet = observerPair.getValue();
            if (!observerInSet.equals(observer) && observerInSet.hashCode() != observer.hashCode()) {
                newObservers.add(observerPair);
            }
        }
        this.observers.clear();
        this.observers.addAll(newObservers);
    }

    protected abstract T getChangedData();

}
