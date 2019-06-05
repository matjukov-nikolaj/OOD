package com.ood.state.with_state;

import com.ood.exception.WrongAmountException;
import com.ood.state.service.QuartersController;
import com.ood.state.service.QuartersControllerImpl;

public class GumballMachineImpl implements GumballMachine {

    private int count;

    private SoldState soldState;

    private SoldOutState soldOutState;

    private NoQuarterState noQuarterState;

    private HasQuarterState hasQuarterState;

    private QuartersController controller;

    private State state;

    public GumballMachineImpl(int numBalls) throws WrongAmountException{
        if (numBalls < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");
        }
        this.count = numBalls;

        this.soldState = new SoldState(this);
        this.soldOutState = new SoldOutState(this);
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);

        this.controller = new QuartersControllerImpl();
        this.state = count > 0 ? this.noQuarterState : this.soldOutState;

    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispence();
    }

    public String toString() {
        String result = "Mighty Gumball, Inc.\r\n" +
                "C++-enabled Standing Gumball Model #2016\r\n" +
                "Inventory: " + count + " gumball" + (count != 1 ? "s" : "") +"\r\n" +
                "Machine is " + state.toString() +"\r\n";
        return result;
    }

    public int getBallCount() {
        return count;
    }

    public void releaseBall() {
        if (count != 0)
        {
            System.out.println("A gumball comes rolling out the slot");
            --count;
        }
    }

    public void setSoldOutState() {
        state = soldOutState;
    }

    public void setNoQuarterState() {
        state = noQuarterState;
    }

    public void setSoldState() {
        state = soldState;
    }

    public void setHasQuarterState() {
        state = hasQuarterState;
    }

    @Override
    public QuartersController getQuartersController() {
        return controller;
    }

    @Override
    public void refill(int ballsCount) throws WrongAmountException {
        if (ballsCount < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");
        }
        this.state.refill(ballsCount);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getBallsCount() {
        return count;
    }
}
