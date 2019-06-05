package com.ood.weatherstation.weatherdata;

import org.apache.log4j.Logger;

public class AngularValuesStatistic {

    private static final Logger LOG = Logger.getLogger(AngularValuesStatistic.class);

    private String name;
    private double sin;
    private double cos;
    private Integer count;

    public AngularValuesStatistic(String name) {
        this.name = name;
        this.sin = 0.0;
        this.cos = 0.0;
        this.count = 0;
    }

    public void display() {
        double avg = this.calculateAverage();
        LOG.info("Average " + this.name + " " + (avg < 0 ? avg + 360 : avg) );
    }

    public void accumulate(Double value) {
        this.sin += Math.sin((value * Math.PI) / 180.0);
        this.cos += Math.cos((value * Math.PI) / 180.0);
        ++count;
    }

    private double calculateAverage() {
        return Math.atan2(this.sin / this.count, this.cos / this.count) * 180 / Math.PI;
    }

}
