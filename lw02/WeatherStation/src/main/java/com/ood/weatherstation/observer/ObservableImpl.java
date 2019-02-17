package com.ood.weatherstation.observer;

import java.util.HashSet;
import java.util.Set;

public abstract class ObservableImpl<T> implements Observable<T> {

     Set<Observer<T>> observers;

     public ObservableImpl() {
          this.observers = new HashSet<>();
     }

     @Override
     public void registerObserver(Observer<T> observer) {
          observers.add(observer);
     }

     @Override
     public void notifyObserver() {
          T data = this.getChangedData();
          for (Observer observer: this.observers) {
               observer.update(data);
          }
     }

     @Override
     public void removeObserver(Observer<T> observer) {
          observers.remove(observer);
     }

     protected abstract T getChangedData();

}
