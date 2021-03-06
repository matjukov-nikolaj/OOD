package com.ood.simuduckfn;

import com.ood.simuduckfn.behavior.fly.FlyBehavior;
import com.ood.simuduckfn.duck.*;
import org.apache.log4j.Logger;

public class App {
    private static final Logger LOG = Logger.getLogger(App.class);

    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        playWithDuck(mallardDuck);

        RedheadDuck redheadDuck = new RedheadDuck();
        playWithDuck(redheadDuck);
        playWithDuck(redheadDuck);
        playWithDuck(redheadDuck);

        RubberDuck rubberDuck = new RubberDuck();
        playWithDuck(rubberDuck);

        DecoyDuck decoyDuck = new DecoyDuck();
        playWithDuck(decoyDuck);

        ModelDuck modelDuck = new ModelDuck();
        playWithDuck(modelDuck);
        modelDuck.setFlyBehavior((new FlyBehavior()).getFlyWithWings());
        playWithDuck(modelDuck);
    }

    private static void playWithDuck(Duck duck) {
        drawDuck(duck);
        duck.quack();
        duck.fly();
        duck.dance();
        duck.swim();
        LOG.info("-----------------------------------------");
    }

    private static void drawDuck(Duck duck) {
        duck.display();
    }
}