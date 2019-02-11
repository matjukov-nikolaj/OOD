package com.ood.weatherstation.observer;

import java.util.Set;

public abstract class ObservableImpl<T> implements Observable<T> {

     Set<Observer<T>> observers;

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
