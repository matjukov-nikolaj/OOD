package com.ood.simuduckfn.behavior.quack;

import com.ood.simuduckfn.function.Function;
import org.apache.log4j.Logger;

public class QuackBehavior {

    private static final Logger LOG = Logger.getLogger(QuackBehavior.class);

    public Function getQuackBehavior() {
        return new Function() {
            @Override
            public void action() {
                LOG.info("Quack Quack!!!");
            }
        };
    }

    public Function getSqueakBehavior() {
        return new Function() {
            @Override
            public void action() {
                LOG.info("Squeak!!!");
            }
        };
    }

    public Function getMuteQuackBehavior() {
        return new Function() {
            @Override
            public void action() {
                LOG.info("I can't quack");
            }
        };
    }

}
