package com.ood.weatherstation;

import com.ood.weatherstation.weatherdata.*;

public class App
{

    public static void main( String[] args )
    {
        WeatherDataOutside wdo = new WeatherDataOutside();
        WeatherDataInside wdi = new WeatherDataInside();

        Display display = new Display();

        wdi.registerObserver(display, 1);

        StatsDisplay statsDisplay = new StatsDisplay();
        wdi.registerObserver(statsDisplay, 2);

        wdi.registerObserver(display, 3);
        wdi.registerObserver(statsDisplay, 5);

        wdi.setMeasurements(3.0, 0.7, 760.0);
        wdi.setMeasurements(4.0, 0.8, 761.0);

        wdi.removeObserver(statsDisplay);

        wdi.setMeasurements(10.0, 0.8, 761.0);
        wdi.setMeasurements(-10.0, 0.8, 761.0);


        DisplayOutside displayOutside = new DisplayOutside();
        StatsDisplayOutside statsDisplayOutside = new StatsDisplayOutside();

        wdo.registerObserver(displayOutside, 1);
        wdo.registerObserver(statsDisplayOutside, 3);
        wdo.setMeasurements(4.0, 0.8, 761.0, 2.0, 180.0);
        wdo.setMeasurements(4.0, 0.8, 761.0, 2.0, 300.0);

    }
}