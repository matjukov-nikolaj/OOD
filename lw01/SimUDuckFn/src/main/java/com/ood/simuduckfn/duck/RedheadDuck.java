package com.ood.simuduckfn.duck;

import com.ood.simuduckfn.behavior.dance.DanceBehavior;
import com.ood.simuduckfn.behavior.fly.FlyBehavior;
import com.ood.simuduckfn.behavior.quack.QuackBehavior;

public class RedheadDuck extends Duck {

    public RedheadDuck() {
        super((new FlyBehavior()).getFlyWithWings(),
                (new QuackBehavior()).getQuackBehavior(),
                (new DanceBehavior()).getMinuetDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a redhead duck.");
    }
}
