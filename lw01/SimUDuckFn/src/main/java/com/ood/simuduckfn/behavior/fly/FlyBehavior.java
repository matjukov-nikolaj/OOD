package com.ood.simuduckfn.behavior.fly;

import com.ood.simuduckfn.function.Function;
import com.ood.simuduckfn.model.FlightCounter;
import org.apache.log4j.Logger;

public class FlyBehavior {

    private static final Logger LOG = Logger.getLogger(FlyBehavior.class);

    public Function getFlyNoWay() {
        return new Function() {
            @Override
            public void action() {
                LOG.info("I can't fly.");
            }
        };
    }

    public Function getFlyWithWings() {
        FlightCounter flightCount = new FlightCounter();
        return () -> {
            flightCount.incCounter();
            LOG.info("Flight: " + flightCount.getCount() + " I'm flying with wings.");
        };
    }

}
