package com.ood.simuduckfn.duck;

import com.ood.simuduckfn.function.Function;
import org.apache.log4j.Logger;

public abstract class Duck {

    protected static final Logger LOG = Logger.getLogger(Duck.class);

    private Function flyBehavior;

    private Function quackBehavior;

    private Function danceBehavior;

    Duck(Function flyBehavior,
         Function quackBehavior,
         Function danceBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
        this.danceBehavior = danceBehavior;
    }

    public final void quack() {
        this.quackBehavior.action();
    }

    public final void swim() {
        LOG.info("I'm swimming.");
    }

    public final void fly() {
        this.flyBehavior.action();
    }

    public final void dance() {
        this.danceBehavior.action();
    }

    public final void setFlyBehavior(Function flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public abstract void display();
}
