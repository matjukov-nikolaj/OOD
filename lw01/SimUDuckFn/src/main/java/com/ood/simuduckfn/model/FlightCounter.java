package com.ood.simuduckfn.model;

public class FlightCounter {

    private Integer count;

    public FlightCounter() {
        this.count = 0;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void incCounter() {
        ++this.count;
    }
}
