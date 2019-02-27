package com.ood.weatherstation.observer;

import com.ood.weatherstation.exception.IncorrectObservableType;
import org.apache.log4j.Logger;

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
        tempObservers.putAll(getCopyOfObservers());
        for (Map.Entry<Integer, List<Observer<T>>> entry : tempObservers.entrySet()) {
            for (Observer<T> observer : entry.getValue()) {
                try {
                    observer.update(this, data);
                } catch (IncorrectObservableType e) {
                    System.out.println(e.getMessage());
                }
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

    private TreeMap<Integer, List<Observer<T>>> getCopyOfObservers() {
        TreeMap<Integer, List<Observer<T>>> copy = new TreeMap<>();
        for (Map.Entry<Integer, List<Observer<T>>> entry : this.observers.entrySet()) {
            List<Observer<T>> value = entry.getValue();
            Integer key = entry.getKey();
            List<Observer<T>> copyOfValue = new ArrayList<>(value);
            copy.put(key, copyOfValue);
        }
        return copy;
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
