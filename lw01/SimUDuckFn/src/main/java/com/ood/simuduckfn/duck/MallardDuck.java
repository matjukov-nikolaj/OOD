package com.ood.simuduckfn.duck;

import com.ood.simuduckfn.behavior.dance.DanceBehavior;
import com.ood.simuduckfn.behavior.fly.FlyBehavior;
import com.ood.simuduckfn.behavior.quack.QuackBehavior;

public class MallardDuck extends Duck {

    public MallardDuck() {
        super((new FlyBehavior()).getFlyWithWings(),
                (new QuackBehavior()).getQuackBehavior(),
                (new DanceBehavior()).getWaltzDanceBehavior());
    }

    @Override
    public void display() {
        LOG.info("I'm a mallard duck.");
    }
}
