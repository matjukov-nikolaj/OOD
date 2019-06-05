package com.ood.coffee.condiment;

import com.ood.coffee.beverage.Beverage;

public class Cinnamon extends CondimentDecorator {

    public Cinnamon(Beverage beverage) {
        super(beverage);
    }

    protected String getCondimentDescription() {
        return "Cinnamon";
    }

    protected double getCondimentCost() {
        return 20;
    }

}
