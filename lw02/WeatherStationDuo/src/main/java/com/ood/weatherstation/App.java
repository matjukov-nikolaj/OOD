package com.ood.weatherstation;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.ObservableType;
import com.ood.weatherstation.weatherdata.Display;
import com.ood.weatherstation.weatherdata.StatsDisplay;
import com.ood.weatherstation.weatherdata.WeatherData;
import org.apache.log4j.Logger;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class);

    public static void main(String[] args) {
        try {
            WeatherData wdo = new WeatherData(ObservableType.OUT);
            WeatherData wdi = new WeatherData(ObservableType.IN);

            Display displayIn = new Display(ObservableType.IN);
            StatsDisplay statsDisplayIn = new StatsDisplay(ObservableType.IN);

            wdi.registerObserver(displayIn, 1);
            wdi.registerObserver(statsDisplayIn, 2);

            Display displayOut = new Display(ObservableType.OUT);
            StatsDisplay statsDisplayOut = new StatsDisplay(ObservableType.OUT);

            wdo.registerObserver(displayOut, 3);
            wdo.registerObserver(statsDisplayOut, 4);

            wdi.setMeasurements(3.0, 0.7, 760.0);
            wdi.setMeasurements(4.0, 0.8, 761.0);

            wdo.setMeasurements(5.0, 0.1, 759.0);
            wdo.setMeasurements(7.0, 0.3, 761.0);

            wdi.removeObserver(statsDisplayIn);

            wdi.setMeasurements(10.0, 0.8, 761.0);
            wdi.setMeasurements(-10.0, 0.8, 761.0);
        } catch (IncorrectObservableType e) {
            LOG.error(e.getMessage());
        }
    }
}