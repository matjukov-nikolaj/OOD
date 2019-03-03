package com.ood.coffee.condiment;

import com.ood.coffee.beverage.Beverage;
import com.ood.exceptions.WrongAmount;

public class CoconutFlakes extends CondimentDecorator {

    private int mass;

    public CoconutFlakes(Beverage beverage, int mass) throws WrongAmount {
        super(beverage);
        if (mass < 0) {
            throw new WrongAmount("Incorrect amount of Coconut Flakes.");
        } else {
            this.mass = mass;
        }
    }

    protected String getCondimentDescription() {
        return "Coconut flakes " + mass + "g";
    }

    protected double getCondimentCost() {
        return 1 * mass;
    }


}
