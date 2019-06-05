package com.ood.weatherstation.weatherdata;

import org.apache.log4j.Logger;

public class ValueStatistic {

    private static final Logger LOG = Logger.getLogger(ValueStatistic.class);

    private Double minValue;
    private Double maxValue;
    private Double storage;
    private Integer count;
    private String name;

    public ValueStatistic(String name) {
        this.minValue = Double.MAX_VALUE;
        this.maxValue = Double.MIN_VALUE;
        this.storage = 0.0;
        this.count = 0;
        this.name = name;
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

    public void display() {
        LOG.info("################");
        LOG.info("Max " + name + " " + this.maxValue);
        LOG.info("Min " + name + " " + this.minValue);
        LOG.info("Average " + name + " " + this.getAverage());
        LOG.info("################");
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
