package com.ood.coffee.beverage;

public class Tea extends BeverageImpl {

    private TeaSort sort;

    public Tea(TeaSort sort) {
        super(TeaSort.toString(sort));
    }

    @Override
    public double getCost() {
        return 30;
    }
}
