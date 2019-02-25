package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.ObservableType;
import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class Display implements Observer<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(Display.class);

    private ObservableType type;

    public Display(ObservableType type) {
        this.type = type;
    }

    public void update(WeatherInfo data) {
        try {
            ObservableTypeAction.actionHandler(this.type,
                    () -> {
                        LOG.info("Inside: ");
                    },
                    () -> {
                        LOG.info("Outside: ");
                    });
            LOG.info("----------------");
            LOG.info("Current Temp " + data.temperature);
            LOG.info("Current Hum " + data.humidity);
            LOG.info("Current Pressure " + data.pressure);
            LOG.info("----------------");
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
