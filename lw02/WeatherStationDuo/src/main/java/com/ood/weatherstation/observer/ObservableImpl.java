package com.ood.weatherstation.observer;

import java.util.*;

public abstract class ObservableImpl<T> implements Observable<T> {

    TreeMap<Integer, List<Observer<T>>> observers;

    public ObservableImpl() {
        this.observers = new TreeMap<>();
    }

    @Override
    public void registerObserver(Observer<T> observer, int priority) {
        this.removeObserver(observer);
        if (this.observers.containsKey(priority)) {
            List<Observer<T>> observerList = this.observers.get(priority);
            observerList.add(observer);
        } else {
            List<Observer<T>> value = new ArrayList<>();
            value.add(observer);
            observers.put(priority, value);
        }
    }

    @Override
    public void notifyObserver() {
        T data = this.getChangedData();
        TreeMap<Integer, List<Observer<T>>> tempObservers = new TreeMap<>(Collections.reverseOrder());
        tempObservers.putAll(this.observers);
        for (Map.Entry<Integer, List<Observer<T>>> entry : tempObservers.entrySet()) {
            for (Observer<T> observer : entry.getValue()) {
                observer.update(data, this);
            }
        }
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        TreeMap<Integer, List<Observer<T>>> observerToRemove = getObserverToRemove(observer);
        if (observerToRemove.isEmpty()) {
            return;
        }
        List<Observer<T>> value = this.observers.get(observerToRemove.firstKey());
        value.remove(observer);
        if (value.isEmpty()) {
            this.observers.remove(observerToRemove.firstKey());
        }
    }

    private TreeMap<Integer, List<Observer<T>>> getObserverToRemove(Observer<T> observer) {
        TreeMap<Integer, List<Observer<T>>> observerToRemove = new TreeMap<>();
        for (Map.Entry<Integer, List<Observer<T>>> entry : this.observers.entrySet()) {
            List<Observer<T>> listObservers = entry.getValue();
            Integer key = entry.getKey();
            for (Observer<T> observerInList : listObservers) {
                if (observerInList == observer) {
                    ArrayList<Observer<T>> listOfObserverToRemove = new ArrayList<>();
                    listOfObserverToRemove.add(observerInList);
                    observerToRemove.put(key, listOfObserverToRemove);
                }
            }
        }
        return observerToRemove;
    }

    protected abstract T getChangedData();

}
