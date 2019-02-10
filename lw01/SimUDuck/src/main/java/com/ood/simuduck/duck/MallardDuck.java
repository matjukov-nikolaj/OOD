package com.ood.simuduck.duck;

import com.ood.simuduck.behavior.dance.WaltzDanceBehavior;
import com.ood.simuduck.behavior.fly.FlyWithWings;
import com.ood.simuduck.behavior.quack.QuackBehaviorImpl;

public class MallardDuck extends Duck {

    public MallardDuck() {
        super(new FlyWithWings(), new QuackBehaviorImpl(), new WaltzDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a mallard duck.");
    }
}
