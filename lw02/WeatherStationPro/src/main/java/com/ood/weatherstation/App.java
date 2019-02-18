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

//        wd.setMeasurements(3.0, 0.7, 760.0, 1.0, 275.0);
        wd.setMeasurements(4.0, 0.8, 761.0, 2.0, 180.0);
        wd.setMeasurements(4.0, 0.8, 761.0, 2.0, 300.0);

        wd.removeObserver(statsDisplay);

        wd.setMeasurements(10.0, 0.8, 761.0, 3.0, 270.0);
        wd.setMeasurements(-10.0, 0.8, 761.0, 4.0, 450.0);
    }
}