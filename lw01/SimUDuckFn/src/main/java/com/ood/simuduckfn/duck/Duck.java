package com.ood.simuduckfn.duck;

import com.ood.simuduckfn.exception.IncorrectInterfaceException;
import com.ood.simuduckfn.function.Function;
import com.ood.simuduckfn.function.FunctionWithParameter;
import com.ood.simuduckfn.model.FlightCounter;
import org.apache.log4j.Logger;

public abstract class Duck {

    protected static final Logger LOG = Logger.getLogger(Duck.class);

    private Object flyBehavior;

    private Function quackBehavior;

    private Function danceBehavior;

    private FlightCounter flightCounter;

    Duck(Object flyBehavior,
         Function quackBehavior,
         Function danceBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
        this.danceBehavior = danceBehavior;
        this.flightCounter = new FlightCounter();
    }

    public void quack() {
        this.quackBehavior.action();
    }

    public void swim() {
        LOG.info("I'm swimming.");
    }

    public void fly() throws IncorrectInterfaceException {
        if (this.flyBehavior instanceof FunctionWithParameter) {
            ((FunctionWithParameter) this.flyBehavior).action(this.flightCounter);
        } else if (this.flyBehavior instanceof Function) {
            ((Function) this.flyBehavior).action();
        } else {
            throw new IncorrectInterfaceException("Incorrect interface: " + this.flyBehavior.getClass());
        }
    }

    public void dance() {
        this.danceBehavior.action();
    }

    public void setFlyBehavior(Object flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public abstract void display();
}
