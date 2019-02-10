package com.ood.simuduckfn.behavior.dance;

import com.ood.simuduckfn.function.Function;
import org.apache.log4j.Logger;

public class DanceBehavior {

    private static final Logger LOG = Logger.getLogger(DanceBehavior.class);

    public Function getWaltzDanceBehavior() {
        return new Function() {
            @Override
            public void action() {
                LOG.info("I'm dancing waltz.");
            }
        };
    }

    public Function getMinuetDanceBehavior() {
        return new Function() {
            @Override
            public void action() {
                LOG.info("I'm dancing minuet.");
            }
        };
    }

    public Function getNoDanceBehavior() {
        return new Function() {
            @Override
            public void action() {
                LOG.info("I don't dance.");
            }
        };
    }

}
