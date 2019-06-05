package com.ood.simuduckfn.duck;

import com.ood.simuduckfn.behavior.dance.DanceBehavior;
import com.ood.simuduckfn.behavior.fly.FlyBehavior;
import com.ood.simuduckfn.behavior.quack.QuackBehavior;
import org.apache.log4j.Logger;

public class DecoyDuck extends Duck {

    private static final Logger LOG = Logger.getLogger(DecoyDuck.class);

    public DecoyDuck() {
        super((new FlyBehavior()).getFlyNoWay(),
                (new QuackBehavior()).getMuteQuackBehavior(),
                (new DanceBehavior()).getNoDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a decoy duck.");
    }

}
