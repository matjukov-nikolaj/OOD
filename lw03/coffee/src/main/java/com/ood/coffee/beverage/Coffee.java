package com.ood.coffee.beverage;

public class Coffee extends BeverageImpl {

    public Coffee(String description) {
        super(description);
    }

    @Override
    public double getCost() {
        return 60;
    }
}
