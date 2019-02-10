package com.ood.simuduck.behavior.quack;

import org.apache.log4j.Logger;

public class MuteQuackBehavior implements QuackBehavior {

    private static final Logger LOG = Logger.getLogger(MuteQuackBehavior.class);

    @Override
    public void quack() {
        LOG.info("I can't quack");
    }
}
