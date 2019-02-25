package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.ObservableType;
import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class SimpleObserver implements Observer<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(SimpleObserver.class);

    private WeatherStatistic inStats = new WeatherStatistic();
    private WeatherStatistic outStats = new WeatherStatistic();

    private BasicDisplayImpl<WeatherInfo> basicDisplay;

    private ObservableType type;

    public SimpleObserver(ObservableType type) {
        this.basicDisplay = new BasicDisplayImpl<>();
        this.type = type;
        try {
            this.basicDisplay.actionHandler(type,
                    () -> {
                        basicDisplay.setAsInsideObservable(this);
                    },
                    () -> {
                        basicDisplay.setAsOutsideObservable(this);
                    });
        } catch (IncorrectObservableType e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void update(WeatherInfo data) {
        try {
            this.basicDisplay.actionHandler(type,
                    () -> {
                        System.out.println("Simple Inside. I have statistic.");
                    },
                    () -> {
                        System.out.println("Simple Outside. I have statistic.");
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
