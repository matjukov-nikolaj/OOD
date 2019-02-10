package com.ood.simuduck.duck;

import com.ood.simuduck.behavior.dance.NoDanceBehavior;
import com.ood.simuduck.behavior.fly.FlyNoWay;
import com.ood.simuduck.behavior.quack.QuackBehaviorImpl;

public class ModelDuck extends Duck {

    public ModelDuck() {
        super(new FlyNoWay(), new QuackBehaviorImpl(), new NoDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a model duck.");
    }
}
