package com.ood.weatherstation;

import com.ood.weatherstation.weatherdata.Display;
import com.ood.weatherstation.weatherdata.StatsDisplay;
import com.ood.weatherstation.weatherdata.WeatherData;
import org.apache.log4j.Logger;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class);

    public static void main(String[] args) {
        WeatherData wdo = new WeatherData();
        WeatherData wdi = new WeatherData();

        Display display = new Display(wdi, wdo);
        StatsDisplay statsDisplay = new StatsDisplay(wdi, wdo);

        wdi.registerObserver(display, 1);
        wdi.registerObserver(statsDisplay, 2);

        wdo.registerObserver(display, 3);
        wdo.registerObserver(statsDisplay, 4);

        wdi.setMeasurements(3.0, 0.7, 760.0);
        wdi.setMeasurements(4.0, 0.8, 761.0);

        wdo.setMeasurements(5.0, 0.1, 759.0);
        wdo.setMeasurements(7.0, 0.3, 761.0);

        wdi.removeObserver(statsDisplay);

        wdi.setMeasurements(10.0, 0.8, 761.0);
        wdi.setMeasurements(-10.0, 0.8, 761.0);

    }
}