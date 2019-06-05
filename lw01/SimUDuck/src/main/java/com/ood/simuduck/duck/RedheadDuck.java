package com.ood.simuduck.duck;

import com.ood.simuduck.behavior.dance.MinuetDanceBehavior;
import com.ood.simuduck.behavior.fly.FlyWithWings;
import com.ood.simuduck.behavior.quack.QuackBehaviorImpl;

public class RedheadDuck extends Duck {

    public RedheadDuck() {
        super(new FlyWithWings(), new QuackBehaviorImpl(), new MinuetDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a redhead duck.");
    }
}
