package com.ood.simuduck.behavior.quack;

import org.apache.log4j.Logger;

public class QuackBehaviorImpl implements QuackBehavior {

    private static final Logger LOG = Logger.getLogger(QuackBehaviorImpl.class);

    @Override
    public void quack() {
        LOG.info("Quack Quack!!!");
    }
}
