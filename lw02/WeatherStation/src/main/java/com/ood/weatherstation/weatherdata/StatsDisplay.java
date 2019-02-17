package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class StatsDisplay implements Observer<WeatherInfo> {

    private static final Logger LOG = Logger.getLogger(StatsDisplay.class);

    private ValueStatistic temperatureStats = new ValueStatistic();
    private ValueStatistic humidityStats = new ValueStatistic();
    private ValueStatistic pressureStats = new ValueStatistic();

    @Override
    public void update(WeatherInfo data) {

        this.temperatureStats.accumulate(data.temperature);
        this.humidityStats.accumulate(data.humidity);
        this.pressureStats.accumulate(data.pressure);

        this.printStatistic(this.temperatureStats, "Temp");
        this.printStatistic(this.humidityStats, "Humidity");
        this.printStatistic(this.pressureStats, "Pressure");
    }

    private void printStatistic(ValueStatistic statistic, String name) {
        LOG.info("################");
        LOG.info("Max " + name + " " + statistic.getMaxValue());
        LOG.info("Min " + name + " " + statistic.getMinValue());
        LOG.info("Average " + name + " " + statistic.getAverage());
        LOG.info("################");
    }
}
