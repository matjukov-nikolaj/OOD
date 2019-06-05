package com.ood.simuduck.behavior.dance;

import org.apache.log4j.Logger;

public class NoDanceBehavior implements DanceBehavior {

    private static final Logger LOG = Logger.getLogger(NoDanceBehavior.class);

    @Override
    public void dance() {
        LOG.info("I don't dance.");
    }
}
