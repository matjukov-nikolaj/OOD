package com.ood.coffee.condiment;

import com.ood.coffee.beverage.Beverage;

public class Syrup extends CondimentDecorator {

    private SyrupType type;

    public Syrup(Beverage beverage, SyrupType type) {
        super(beverage);
        this.type = type;
    }

    protected String getCondimentDescription() {
        return this.type.toString() + " syrup";
    }

    protected double getCondimentCost() {
        return 15;
    }

}
