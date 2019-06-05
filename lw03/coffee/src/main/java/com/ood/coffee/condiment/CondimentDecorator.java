package com.ood.coffee.condiment;

import com.ood.coffee.beverage.Beverage;

public abstract class CondimentDecorator implements Beverage {

    private Beverage beverage;

    protected CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + ", " + this.getCondimentDescription();
    }

    @Override
    public double getCost() {
        return beverage.getCost() + this.getCondimentCost();
    }

    abstract String getCondimentDescription();

    abstract double getCondimentCost();

}
