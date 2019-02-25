package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.exception.IncorrectObservableType;
import com.ood.weatherstation.observer.ObservableImpl;
import com.ood.weatherstation.observer.ObservableType;
import com.ood.weatherstation.observer.Observer;

public class WeatherData extends ObservableImpl<WeatherInfo> {

    private ObservableType type;

    private double temperature = 0.0;

    private double humidity = 0.0;

    private double pressure = 760.0;

    public WeatherData(ObservableType type) {
        this.type = type;
    }

    @Override
    public void registerObserver(Observer<WeatherInfo> observer, int priority) throws IncorrectObservableType{
        if (observer.getType() != this.type) {
            throw new IncorrectObservableType("Incorrect observable type.");
        }
        super.registerObserver(observer, priority);
    }

    @Override
    public void removeObserver(Observer<WeatherInfo> observer) throws IncorrectObservableType {
        if (observer.getType() != this.type) {
            throw new IncorrectObservableType("Incorrect observable type.");
        }
        super.removeObserver(observer);
    }

    public double getTemperature() {
        return this.temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void measurementsChanged() {
        notifyObserver();
    }

    public void setMeasurements(double temp, double humidity, double pressure) {
        this.humidity = humidity;
        this.temperature = temp;
        this.pressure = pressure;

        measurementsChanged();
    }

    @Override
    protected WeatherInfo getChangedData() {
        WeatherInfo info = new WeatherInfo();
        info.temperature = this.getTemperature();
        info.humidity = this.getHumidity();
        info.pressure = this.getPressure();
        return info;
    }
}
