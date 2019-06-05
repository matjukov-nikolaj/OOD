package com.ood.state.with_state;

import com.ood.exception.WrongAmountException;

public class HasQuarterState implements State {

    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        gumballMachine.getQuartersController().addQuarter();
    }

    @Override
    public void ejectQuarter() {
        gumballMachine.getQuartersController().returnQuarters();
        gumballMachine.setNoQuarterState();
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned...");
        gumballMachine.getQuartersController().useQuarter();
        gumballMachine.setSoldState();
    }

    @Override
    public void dispence() {
        System.out.println("No gumball dispensed");
    }

    @Override
    public String toString() {
        return "waiting for turn of crank";
    }

    @Override
    public void refill(int ballsCount) throws WrongAmountException {
        if (ballsCount < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");
        }
        if (ballsCount == 0) {
            gumballMachine.setSoldOutState();
        }
    }
}
