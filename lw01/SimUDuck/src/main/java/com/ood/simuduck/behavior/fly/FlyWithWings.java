package com.ood.simuduck.behavior.fly;

import org.apache.log4j.Logger;

public class FlyWithWings implements FlyBehavior {

    private static final Logger LOG = Logger.getLogger(FlyWithWings.class);

    private Integer flightCount = 0;

    @Override
    public void fly() {
        ++this.flightCount;
        LOG.info("Flight: " + this.flightCount + " I'm flying with wings.");
    }
}
