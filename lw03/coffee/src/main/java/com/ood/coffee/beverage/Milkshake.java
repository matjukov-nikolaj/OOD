package com.ood.coffee.beverage;

public class Milkshake extends BeverageImpl {

    private MilkshakeType type;

    public Milkshake(MilkshakeType type) {
        super(MilkshakeType.toString(type) + "Milkshake");
        this.type = type;
    }

    @Override
    public double getCost() {
        return MilkshakeType.getCostOfType(this.type);
    }

}
