package com.ood.coffee.condiment;

import com.ood.coffee.beverage.Beverage;
import com.ood.exceptions.WrongAmountException;

public class ChocolateCrumbs extends CondimentDecorator {

    private int mass;

    public ChocolateCrumbs(Beverage beverage, int mass) throws WrongAmountException {
        super(beverage);
        if (mass < 0) {
            throw new WrongAmountException("Incorrect amount of Chocolate Crumbs.");
        } else {
            this.mass = mass;
        }
    }

    protected String getCondimentDescription() {
        return "Chocolate crumbs " + mass + "g";
    }

    protected double getCondimentCost() {
        return 2 * mass;
    }

}
