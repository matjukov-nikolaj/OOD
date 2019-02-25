package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.ObservableType;
import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class NormalObserver implements Observer<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(Display.class);

    private BasicDisplayImpl<WeatherInfo> basicDisplay;

    private ObservableType type;

    public NormalObserver(ObservableType type) throws IncorrectObservableType {
        this.basicDisplay = new BasicDisplayImpl<>();
        this.type = type;
        this.basicDisplay.actionHandler(type,
                () -> {
                    this.basicDisplay.setAsInsideObservable(this);
                },
                () -> {
                    this.basicDisplay.setAsOutsideObservable(this);
                });
    }

    public void update(WeatherInfo data) {
        try {
            this.basicDisplay.actionHandler(this.type,
                    () -> {
                        System.out.println("Normal Inside.");
                    },
                    () -> {
                        System.out.println("Normal Outside.");
                    });
        } catch (IncorrectObservableType e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public ObservableType getType() {
        return type;
    }

    @Override
    public void setType(ObservableType type) {
        this.type = type;
    }


}
