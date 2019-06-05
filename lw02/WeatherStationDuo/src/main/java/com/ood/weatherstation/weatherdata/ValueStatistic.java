package com.ood.weatherstation.weatherdata;

import org.apache.log4j.Logger;

public class ValueStatistic {

    private static final Logger LOG = Logger.getLogger(ValueStatistic.class);

    private double minValue;
    private double maxValue;
    private double storage;
    private int count;
    private String name;

    public ValueStatistic(String name) {
        this.minValue = Double.MAX_VALUE;
        this.maxValue = Double.MIN_VALUE;
        this.storage = 0.0;
        this.count = 0;
        this.name = name;
    }

    public void display() {
        LOG.info("################");
        LOG.info("Max " + this.name + " " + this.maxValue);
        LOG.info("Min " + this.name + " " + this.minValue);
        LOG.info("Average " + this.name + " " + this.getAverage());
        LOG.info("################");
    }

    public void accumulate(double value) {
        if (value < this.minValue) {
            this.minValue = value;
        }
        if (value > this.maxValue) {
            this.maxValue = value;
        }
        this.storage += value;
        ++this.count;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getAverage() {
        return this.storage / this.count;
    }
}
