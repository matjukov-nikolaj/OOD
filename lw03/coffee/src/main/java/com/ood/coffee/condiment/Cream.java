package com.ood.coffee.condiment;

import com.ood.coffee.beverage.Beverage;

public class Cream extends CondimentDecorator {

    public Cream(Beverage beverage) {
        super(beverage);
    }

    protected String getCondimentDescription() {
        return "Cream";
    }

    protected double getCondimentCost() {
        return 25;
    }

}
