package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observable;
import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class StatsDisplay implements Observer<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(StatsDisplay.class);

    private WeatherStatistic inStats = new WeatherStatistic();
    private WeatherStatistic outStats = new WeatherStatistic();

    private Observable<WeatherInfo> inObservable;

    private Observable<WeatherInfo> outObservable;

    public StatsDisplay(Observable<WeatherInfo> inObservable,
                   Observable<WeatherInfo> outObservable) {
        this.inObservable = inObservable;
        this.outObservable = outObservable;
    }
    @Override
    public void update(Observable<WeatherInfo> observable, WeatherInfo data) {
        if (observable == this.inObservable) {
            LOG.info("Inside: ");
            inStats.update(data);
            inStats.display();
        } else if (observable == this.outObservable) {
            LOG.info("Outside: ");
            outStats.update(data);
            outStats.display();
        }
    }

}
