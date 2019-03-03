package com.ood.coffee.beverage;

public class Milkshake extends BeverageImpl {

    private MilkshakeType type;

    public Milkshake(MilkshakeType type) {
        super(type.toString() + "Milkshake");
        this.type = type;
    }

    @Override
    public double getCost() {
        return this.type.getCostOfType();
    }

}
