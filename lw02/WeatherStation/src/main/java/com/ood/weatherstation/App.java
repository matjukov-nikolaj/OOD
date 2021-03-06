package com.ood.weatherstation;

import com.ood.weatherstation.weatherdata.Display;
import com.ood.weatherstation.weatherdata.StatsDisplay;
import com.ood.weatherstation.weatherdata.WeatherData;

public class App
{

    public static void main( String[] args )
    {
        WeatherData wd = new WeatherData();

        Display display = new Display();

        wd.registerObserver(display, 1);

        StatsDisplay statsDisplay = new StatsDisplay();
        wd.registerObserver(statsDisplay, 2);
        wd.registerObserver(statsDisplay, 1);
        wd.registerObserver(statsDisplay, 3);

        wd.setMeasurements(3.0, 0.7, 760.0);
        wd.setMeasurements(4.0, 0.8, 761.0);

        wd.removeObserver(statsDisplay);

        wd.setMeasurements(10.0, 0.8, 761.0);
        wd.setMeasurements(-10.0, 0.8, 761.0);
    }
}