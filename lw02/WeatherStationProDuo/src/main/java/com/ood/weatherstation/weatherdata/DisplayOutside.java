package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class DisplayOutside implements Observer<WeatherInfoWithWind> {

    private static final Logger LOG = Logger.getLogger(DisplayOutside.class);

    public void update(WeatherInfoWithWind data)
    {
        LOG.info("----------------");
        LOG.info("Current Temp " + data.temperature);
        LOG.info("Current Hum " + data.humidity);
        LOG.info("Current Pressure " + data.pressure );
        LOG.info("Current Wind Speed " + data.windSpeed);
        LOG.info("Current Wind Direction " + data.windDirection );
        LOG.info("----------------");
    }

}
