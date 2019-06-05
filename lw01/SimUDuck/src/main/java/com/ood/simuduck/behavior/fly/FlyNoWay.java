package com.ood.simuduck.behavior.fly;

import org.apache.log4j.Logger;

public class FlyNoWay implements FlyBehavior {

    private static final Logger LOG = Logger.getLogger(FlyNoWay.class);

    @Override
    public void fly() {
        LOG.info("I can't fly.");
    }
}
