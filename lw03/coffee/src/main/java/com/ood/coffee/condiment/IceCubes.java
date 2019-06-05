package com.ood.coffee.condiment;

import com.ood.coffee.beverage.Beverage;
import com.ood.exceptions.WrongAmountException;

public class IceCubes extends CondimentDecorator {

    private int quantity;

    private IceCubeType type;


    public IceCubes(Beverage beverage, int quantity, IceCubeType type) throws WrongAmountException {
        super(beverage);
        if (quantity < 0) {
            throw new WrongAmountException("Incorrect amount of Ice cubes.");
        } else {
            this.quantity = quantity;
        }
        this.type = type;
    }

    protected String getCondimentDescription() {
        String result = "";
        result += IceCubeType.toString(this.type);
        result += " ice cubes x " + this.quantity;
        return result;
    }

    protected double getCondimentCost() {
        return (this.type == IceCubeType.DRY ? 10 : 5) * this.quantity;
    }



}
