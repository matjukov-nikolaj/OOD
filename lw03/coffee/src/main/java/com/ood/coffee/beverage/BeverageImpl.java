package com.ood.coffee.beverage;

public class BeverageImpl implements Beverage {

    private String description;

    public BeverageImpl(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getCost() {
        return 0;
    }
}
