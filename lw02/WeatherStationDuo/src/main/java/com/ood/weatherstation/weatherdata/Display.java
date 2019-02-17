package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observable;
import org.apache.log4j.Logger;

public class Display extends BasicDisplayImpl<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(Display.class);

    public void update(WeatherInfo data, Observable<WeatherInfo> observable)
    {
        switch (this.getType(observable)) {
            case IN:
                LOG.info("Inside: ");
                break;
            case OUT:
                LOG.info("Outside: ");
                break;
            default:
                break;
        }
        LOG.info("----------------");
        LOG.info("Current Temp " + data.temperature);
        LOG.info("Current Hum " + data.humidity);
        LOG.info("Current Pressure " + data.pressure );
        LOG.info("----------------");
    }

}
