package com.ood.simuduck.duck;

import com.ood.simuduck.behavior.dance.DanceBehavior;
import com.ood.simuduck.behavior.fly.FlyBehavior;
import com.ood.simuduck.behavior.quack.QuackBehavior;
import org.apache.log4j.Logger;

public abstract class Duck {

    protected static final Logger LOG = Logger.getLogger(Duck.class);

    private FlyBehavior flyBehavior;

    private QuackBehavior quackBehavior;

    private DanceBehavior danceBehavior;

    Duck(FlyBehavior flyBehavior,
                     QuackBehavior quackBehavior,
                     DanceBehavior danceBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
        this.danceBehavior = danceBehavior;
    }

    public final void quack() {
        this.quackBehavior.quack();
    }

    public final void swim() {
        LOG.info("I'm swimming.");
    }

    public final void fly() {
        this.flyBehavior.fly();
    }

    public final void dance() {
        this.danceBehavior.dance();
    }

    public final void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public abstract void display();
}
