package com.ood.simuduckfn.duck;

import com.ood.simuduckfn.behavior.dance.DanceBehavior;
import com.ood.simuduckfn.behavior.fly.FlyBehavior;
import com.ood.simuduckfn.behavior.quack.QuackBehavior;

public class ModelDuck extends Duck {

    public ModelDuck() {
        super((new FlyBehavior()).getFlyNoWay(),
                (new QuackBehavior()).getQuackBehavior(),
                (new DanceBehavior()).getNoDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a model duck.");
    }
}
