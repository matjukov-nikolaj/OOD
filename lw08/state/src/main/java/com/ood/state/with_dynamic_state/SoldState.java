package com.ood.state.with_dynamic_state;

public class SoldState implements State {

    private GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        gumballMachine.getQuartersController().addQuarter();
    }

    @Override
    public void ejectQuarter() {
        gumballMachine.getQuartersController().returnQuarters();
    }

    @Override
    public void turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball");
    }

    @Override
    public void dispence() {
        gumballMachine.releaseBall();
        if (gumballMachine.getBallCount() == 0)
        {
            System.out.println("Oops, out of gumballs");
            gumballMachine.setSoldOutState();
        } else if (gumballMachine.getQuartersController().getQuartersCount() == 0) {
            gumballMachine.setNoQuarterState();
        } else {
            gumballMachine.setHasQuarterState();
        }
    }

    @Override
    public String toString() {
        return "delivering a gumball";
    }

    @Override
    public void refill(int ballsCount) {
        System.out.println("Cant refill machine in SOLD state");
    }
}
