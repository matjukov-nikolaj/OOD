package com.ood.state.with_dynamic_state;

import com.ood.exception.WrongAmountException;
import com.ood.state.service.QuartersController;

public class GumballMachineImpl implements GumballMachine {

    private int count;

    private State state;

    private QuartersController controller;

    public GumballMachineImpl(int numBalls) throws WrongAmountException{
        if (numBalls < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");
        }
        this.count = numBalls;
        if (count > 0) {
            setNoQuarterState();
        } else {
            setSoldOutState();
        }
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
            System.out.println("A gumball comes rolling out the slot...");
            --count;
        }
    }

    public void setSoldOutState() {
        state = new SoldOutState(this);
    }

    public void setNoQuarterState() {
        state = new NoQuarterState(this);
    }

    public void setSoldState() {
        state = new SoldState(this);
    }

    public void setHasQuarterState() {
        state = new HasQuarterState(this);
    }

    public QuartersController getQuartersController() {
        return controller;
    }

    @Override
    public void setBallsCount(int ballsCount) throws WrongAmountException {
        if (ballsCount < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");
        }
        this.count = ballsCount;
    }

}
