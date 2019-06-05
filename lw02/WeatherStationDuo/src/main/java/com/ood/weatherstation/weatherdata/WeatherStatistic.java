package com.ood.weatherstation.weatherdata;

public class WeatherStatistic {

    private ValueStatistic temperature;
    private ValueStatistic humidity;
    private ValueStatistic pressure;

    WeatherStatistic() {
        this.temperature = new ValueStatistic("Temp");
        this.humidity = new ValueStatistic("Humidity");
        this.pressure = new ValueStatistic("Pressure");
    }

    public void update(WeatherInfo data) {
        this.pressure.accumulate(data.pressure);
        this.humidity.accumulate(data.humidity);
        this.temperature.accumulate(data.temperature);
    }

    public void display() {
        this.temperature.display();
        this.humidity.display();
        this.pressure.display();
    }

}
