package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.ObservableImpl;

public class WeatherInfoOutside extends ObservableImpl<WeatherInfoWithWind> {

    private double temperature = 0.0;

    private double humidity = 0.0;

    private double pressure = 760.0;

    private double windSpeed = 0.0;

    private double windDir = 0.0;

    public double getTemperature() {
        return this.temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDir() {
        return windDir;
    }

    public void measurementsChanged() {
        notifyObserver();
    }

    public void setMeasurements(double temp, double humidity, double pressure, double windSpeed, double windDir) {
        this.humidity = humidity;
        this.temperature = temp;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDir = windDir;

        measurementsChanged();
    }

    @Override
    protected WeatherInfoWithWind getChangedData() {
        WeatherInfoWithWind info = new WeatherInfoWithWind();
        info.temperature = this.getTemperature();
        info.humidity = this.getHumidity();
        info.pressure = this.getPressure();
        info.windSpeed = this.getWindSpeed();
        info.windDirection = this.getWindDir();
        return info;
    }

}
