package com.ood.simuduckfn.duck;

import com.ood.simuduckfn.behavior.dance.DanceBehavior;
import com.ood.simuduckfn.behavior.fly.FlyBehavior;
import com.ood.simuduckfn.behavior.quack.QuackBehavior;

public class RubberDuck extends Duck {

    public RubberDuck() {
        super((new FlyBehavior()).getFlyNoWay(),
                (new QuackBehavior()).getSqueakBehavior(),
                (new DanceBehavior()).getNoDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a rubber duck.");
    }
}
