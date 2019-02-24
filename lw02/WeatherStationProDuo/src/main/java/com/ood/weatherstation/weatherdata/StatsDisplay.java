package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class StatsDisplay implements Observer<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(StatsDisplay.class);

    private ValueStatistic temperatureStats = new ValueStatistic("Temp");
    private ValueStatistic humidityStats = new ValueStatistic("Humidity");
    private ValueStatistic pressureStats = new ValueStatistic("Pressure");

    public void update(WeatherInfo data) {

        this.temperatureStats.accumulate(data.temperature);
        this.humidityStats.accumulate(data.humidity);
        this.pressureStats.accumulate(data.pressure);

        this.temperatureStats.display();
        this.humidityStats.display();
        this.pressureStats.display();
    }

}
