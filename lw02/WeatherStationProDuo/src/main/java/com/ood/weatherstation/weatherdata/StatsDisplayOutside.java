package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.Observer;
import org.apache.log4j.Logger;

public class StatsDisplayOutside implements Observer<WeatherInfoWithWind> {

    private static final Logger LOG = Logger.getLogger(StatsDisplay.class);

    private ValueStatistic temperatureStats = new ValueStatistic("Temp");
    private ValueStatistic humidityStats = new ValueStatistic("Humidity");
    private ValueStatistic pressureStats = new ValueStatistic("Pressure");
    private ValueStatistic windSpeed = new ValueStatistic("Wind Speed");
    private AngularValuesStatistic windDir = new AngularValuesStatistic("Wind Direction");

    @Override
    public void update(WeatherInfoWithWind data) {

        this.temperatureStats.accumulate(data.temperature);
        this.humidityStats.accumulate(data.humidity);
        this.pressureStats.accumulate(data.pressure);
        this.windSpeed.accumulate(data.windDirection);
        this.windDir.accumulate(data.windDirection);


        this.temperatureStats.display();
        this.humidityStats.display();
        this.pressureStats.display();
        this.windSpeed.display();
        this.windDir.display();
    }

}
