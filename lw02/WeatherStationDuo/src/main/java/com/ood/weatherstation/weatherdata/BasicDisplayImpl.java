package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.function.Function;
import com.ood.weatherstation.observer.ObservableType;
import com.ood.weatherstation.observer.Observer;

public class BasicDisplayImpl<T> implements BasicDisplay<T> {

    public Observer<T> insideObservable;

    public Observer<T> outsideObservable;

    @Override
    public void setAsInsideObservable(Observer<T> observable) {
        this.insideObservable = observable;
    }

    @Override
    public void setAsOutsideObservable(Observer<T> observable) {
        this.outsideObservable = observable;
    }

    public void actionHandler(ObservableType type, Function inAction, Function outAction) throws IncorrectObservableType {
        switch (type) {
            case IN:
                inAction.perform();
                break;
            case OUT:
                outAction.perform();
                break;
            default:
                throw new IncorrectObservableType("Incorrect observable type");
        }
    }

}
