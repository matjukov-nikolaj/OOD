package com.ood.coffee.beverage;

public class Cappuccino extends Coffee {

    private CoffeeType type;

    public Cappuccino(CoffeeType type) {
        super(type.toString() + "Cappuccino");
        this.type = type;
    }

    @Override
    public double getCost() {
        return this.type == CoffeeType.STANDARD ? 80 : 120;
    }
}
