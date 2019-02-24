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

        wd.setMeasurements(4.0, 0.8, 761.0, 2.0, 180.0);
        wd.setMeasurements(4.0, 0.8, 761.0, 2.0, 300.0);
    }
}