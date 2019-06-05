package com.ood.state.with_state;

import com.ood.exception.WrongAmountException;

public class SoldOutState implements State {

    private GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can't insert a quarter, the machine is sold out");
    }

    @Override
    public void ejectQuarter() {
        if (gumballMachine.getQuartersController().getQuartersCount() == 0) {
            System.out.println("You can't eject, you haven't inserted a quarter yet");
        } else {
            gumballMachine.getQuartersController().returnQuarters();
            gumballMachine.setNoQuarterState();
        }
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned but there's no gumballs");
    }

    @Override
    public void dispence() {
        System.out.println("No gumball dispensed");
    }

    @Override
    public String toString() {
        return "sold out";
    }

    public void refill(int ballsCount) throws WrongAmountException {
        if (ballsCount < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");
        }
        if (ballsCount != 0 && gumballMachine.getQuartersController().getQuartersCount() == 0) {
            gumballMachine.setNoQuarterState();
        }

    }

}
