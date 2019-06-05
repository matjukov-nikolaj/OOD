package com.ood.simuduck.behavior.quack;

import org.apache.log4j.Logger;

public class SqueakBehavior implements QuackBehavior {

    private static final Logger LOG = Logger.getLogger(SqueakBehavior.class);

    @Override
    public void quack() {
        LOG.info("Squeak!!!");
    }
}
