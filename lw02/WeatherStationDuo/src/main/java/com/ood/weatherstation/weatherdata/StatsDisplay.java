package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.ObservableType;
import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class StatsDisplay implements Observer<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(StatsDisplay.class);

    private WeatherStatistic inStats = new WeatherStatistic();
    private WeatherStatistic outStats = new WeatherStatistic();

    private BasicDisplayImpl<WeatherInfo> basicDisplay;

    private ObservableType type;

    public StatsDisplay(ObservableType type) {
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
                        LOG.info("Inside: ");
                        inStats.update(data);
                        inStats.display();
                    },
                    () -> {
                        LOG.info("Outside: ");
                        outStats.update(data);
                        outStats.display();
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
