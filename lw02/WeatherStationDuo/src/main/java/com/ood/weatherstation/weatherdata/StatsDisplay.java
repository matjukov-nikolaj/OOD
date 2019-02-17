package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observable;
import org.apache.log4j.Logger;

public class StatsDisplay extends BasicDisplayImpl<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(StatsDisplay.class);

    private WeatherStatistic inStats = new WeatherStatistic();
    private WeatherStatistic outStats = new WeatherStatistic();

    @Override
    public void update(WeatherInfo data, Observable<WeatherInfo> observable) {
        switch (this.getType(observable))
        {
            case IN:
                LOG.info("Inside: ");
                inStats.update(data);
                inStats.display();
                break;
            case OUT:
                LOG.info("Outside: ");
                outStats.update(data);
                outStats.display();
                break;
            default:
                break;
        }
    }

}
