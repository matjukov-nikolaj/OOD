package com.ood.simuduckfn.behavior.fly;

import com.ood.simuduckfn.function.Function;
import com.ood.simuduckfn.function.FunctionWithParameter;
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

    public FunctionWithParameter getFlyWithWings() {
        return new FunctionWithParameter<FlightCounter>() {
            @Override
            public void action(FlightCounter flightCounter) {
                flightCounter.incCounter();
                LOG.info("Flight: " + flightCounter.getCount() + " I'm flying with wings.");
            }
        };
    }

}
