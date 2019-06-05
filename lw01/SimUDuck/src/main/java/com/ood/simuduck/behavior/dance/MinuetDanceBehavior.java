package com.ood.simuduck.behavior.dance;

import org.apache.log4j.Logger;

public class MinuetDanceBehavior implements DanceBehavior {

    private static final Logger LOG = Logger.getLogger(MinuetDanceBehavior.class);

    @Override
    public void dance() {
        LOG.info("I'm dancing minuet.");
    }
}
