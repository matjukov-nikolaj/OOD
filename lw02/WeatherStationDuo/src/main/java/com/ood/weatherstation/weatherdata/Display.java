package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.Observable;
import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class Display implements Observer<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(Display.class);

    private Observable<WeatherInfo> inObservable;

    private Observable<WeatherInfo> outObservable;

    public Display(Observable<WeatherInfo> inObservable,
                   Observable<WeatherInfo> outObservable) {
        this.inObservable = inObservable;
        this.outObservable = outObservable;
    }

    public void update(Observable<WeatherInfo> observable, WeatherInfo data) throws IncorrectObservableType {
        if (observable == this.inObservable) {
            LOG.info("Inside: ");
        } else if (observable == this.outObservable) {
            LOG.info("Outside: ");
        } else {
            throw new IncorrectObservableType("Incorrect observable type.");
        }
        LOG.info("----------------");
        LOG.info("Current Temp " + data.temperature);
        LOG.info("Current Hum " + data.humidity);
        LOG.info("Current Pressure " + data.pressure);
        LOG.info("----------------");
    }

}
