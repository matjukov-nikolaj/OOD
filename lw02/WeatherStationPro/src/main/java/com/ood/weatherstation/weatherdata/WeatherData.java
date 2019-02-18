package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.ObservableImpl;

public class WeatherData extends ObservableImpl<WeatherInfo> {

    private Double temperature = 0.0;

    private Double humidity = 0.0;

    private Double pressure = 760.0;

    private Double windSpeed = 0.0;

    private Double windDir = 0.0;

    public Double getTemperature() {
        return this.temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Double getWindDir() {
        return windDir;
    }

    public void measurementsChanged() {
        notifyObserver();
    }

    public void setMeasurements(Double temp, Double humidity, Double pressure, Double windSpeed, Double windDir) {
        this.humidity = humidity;
        this.temperature = temp;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDir = windDir;

        measurementsChanged();
    }

    @Override
    protected WeatherInfo getChangedData() {
        WeatherInfo info = new WeatherInfo();
        info.temperature = this.getTemperature();
        info.humidity = this.getHumidity();
        info.pressure = this.getPressure();
        info.windSpeed = this.getWindSpeed();
        info.windDirection = this.getWindDir();
        return info;
    }
}
