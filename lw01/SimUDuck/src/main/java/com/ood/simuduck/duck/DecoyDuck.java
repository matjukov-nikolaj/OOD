package com.ood.simuduck.duck;

import com.ood.simuduck.behavior.dance.NoDanceBehavior;
import com.ood.simuduck.behavior.fly.FlyNoWay;
import com.ood.simuduck.behavior.quack.MuteQuackBehavior;
import org.apache.log4j.Logger;

public class DecoyDuck extends Duck {

    private static final Logger LOG = Logger.getLogger(DecoyDuck.class);

    public DecoyDuck() {
        super(new FlyNoWay(), new MuteQuackBehavior(), new NoDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a decoy duck.");
    }
}
