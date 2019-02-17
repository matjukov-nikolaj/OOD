package com.ood.weatherstation;

import com.ood.weatherstation.observer.ObservableType;
import com.ood.weatherstation.weatherdata.Display;
import com.ood.weatherstation.weatherdata.StatsDisplay;
import com.ood.weatherstation.weatherdata.WeatherData;

public class App
{

    public static void main( String[] args )
    {
        WeatherData wdo = new WeatherData(ObservableType.OUT);
        WeatherData wdi = new WeatherData(ObservableType.IN);

        Display display = new Display();

        wdi.registerObserver(display, 1);

        StatsDisplay statsDisplay = new StatsDisplay();
        wdi.registerObserver(statsDisplay, 2);
        wdo.registerObserver(statsDisplay, 1);

        wdi.setMeasurements(3.0, 0.7, 760.0);
        wdi.setMeasurements(4.0, 0.8, 761.0);

        wdo.setMeasurements(5.0, 0.1, 759.0);
        wdo.setMeasurements(7.0, 0.3, 761.0);

        wdi.removeObserver(statsDisplay);

        wdi.setMeasurements(10.0, 0.8, 761.0);
        wdi.setMeasurements(-10.0, 0.8, 761.0);
    }
}