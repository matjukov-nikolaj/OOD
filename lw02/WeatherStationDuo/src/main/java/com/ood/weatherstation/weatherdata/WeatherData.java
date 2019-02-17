package com.ood.weatherstation.weatherdata;

import com.ood.weatherstation.observer.ObservableImpl;
import com.ood.weatherstation.observer.ObservableType;

public class WeatherData extends ObservableImpl<WeatherInfo> {

    public WeatherData(ObservableType type) {
        this.type = type;
    }

    private ObservableType type;

    private Double temperature = 0.0;

    private Double humidity = 0.0;

    private Double pressure = 760.0;

    public void registerObserver(BasicDisplay<WeatherInfo> observer, Integer priority) {
        super.registerObserver(observer, priority);
        switch (this.type)
        {
            case IN:
                observer.registerInsideObservable(this);
                break;
            case OUT:
                observer.registerOutsideObservable(this);
                break;
            default:
                break;
        }
    }

    public void removeObserver(BasicDisplay<WeatherInfo> observer) {
        super.removeObserver(observer);
        observer.unregisterObservable(this);
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void measurementsChanged() {
        notifyObserver();
    }

    public void setMeasurements(Double temp, Double humidity, Double pressure) {
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
