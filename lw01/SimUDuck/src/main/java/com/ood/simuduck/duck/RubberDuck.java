package com.ood.simuduck.duck;

import com.ood.simuduck.behavior.dance.NoDanceBehavior;
import com.ood.simuduck.behavior.fly.FlyNoWay;
import com.ood.simuduck.behavior.quack.SqueakBehavior;

public class RubberDuck extends Duck {

    public RubberDuck() {
        super(new FlyNoWay(), new SqueakBehavior(), new NoDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a rubber duck.");
    }
}
