package com.ood.coffee.condiment;

import com.ood.coffee.beverage.Beverage;
import com.ood.exceptions.WrongAmountException;

public class Chocolate extends CondimentDecorator {

    private int quantity;

    public Chocolate(Beverage beverage, int quantity) throws WrongAmountException {
        super(beverage);
        if (quantity < 0 || quantity > 5) {
            throw new WrongAmountException("Max quantity of chocolate is 5.");
        }
        this.quantity = quantity;
    }

    protected String getCondimentDescription() {
        return "Chocolate quantity x " + this.quantity;
    }

    protected double getCondimentCost() {
        return 10 * this.quantity;
    }

}
