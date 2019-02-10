package com.ood.simuduck.behavior.dance;

import org.apache.log4j.Logger;

public class WaltzDanceBehavior implements DanceBehavior {

    private static final Logger LOG = Logger.getLogger(WaltzDanceBehavior.class);

    @Override
    public void dance() {
        LOG.info("I'm dancing waltz.");
    }
}
