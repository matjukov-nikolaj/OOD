package com.ood.coffee.condiment;

import com.ood.coffee.beverage.Beverage;
import com.ood.exceptions.WrongAmountException;

public class Lemon extends CondimentDecorator {

    private int quantity;

    public Lemon(Beverage beverage, int quantity) throws WrongAmountException {
        super(beverage);
        if (quantity < 1) {
            throw new WrongAmountException("Incorrect amount of lemon.");
        } else {
            this.quantity = quantity;
        }
    }

    protected String getCondimentDescription() {
        return "Lemon x " + this.quantity;
    }

    protected double getCondimentCost() {
        return (10 * this.quantity);
    }

}
