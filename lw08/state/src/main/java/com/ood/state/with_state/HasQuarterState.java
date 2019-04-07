package com.ood.state.with_state;

public class HasQuarterState implements State {

    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can't insert another quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Quarter returned");
        gumballMachine.setNoQuarterState();
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned...");
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

}
