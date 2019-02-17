package com.ood.weatherstation.weatherdata;

public class ValueStatistic {

    private Double minValue;
    private Double maxValue;
    private Double storage;
    private Integer count;

    public ValueStatistic() {
        this.minValue = Double.MAX_VALUE;
        this.maxValue = Double.MIN_VALUE;
        this.storage = 0.0;
        this.count = 0;
    }

    public void accumulate(Double value) {
        if (value < this.minValue) {
            this.minValue = value;
        }
        if (value > this.maxValue) {
            this.maxValue = value;
        }
        this.storage += value;
        ++this.count;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public Double getAverage() {
        return this.storage / this.count;
    }
}
