package com.ood.state.naive;

import com.ood.exception.WrongAmountException;
import com.ood.state.quarters.QuartersController;
import com.ood.state.quarters.QuartersControllerImpl;

public class GumballMachine {

    private int count;

    private QuartersControllerImpl controller;

    private State state;

    public GumballMachine(int count) throws WrongAmountException {
        if (count < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");

        }
        this.count = count;
        this.state = count > 0 ? State.NO_QUARTER : State.SOLD_OUT;
        this.controller = new QuartersControllerImpl();
    }

    public void insertQuarter() {
        switch (state) {
            case SOLD_OUT:
                System.out.println("You can't insert a quarter, the machine is sold out");
                break;
            case SOLD:
                controller.addQuarter();
                break;
            case NO_QUARTER:
                controller.addQuarter();
                state = State.HAS_QUARTER;
                break;
            case HAS_QUARTER:
                controller.addQuarter();
                break;
        }
    }

    public void ejectQuarter() {
        switch (state) {
            case SOLD_OUT:
                if (controller.getQuartersCount() == 0) {
                    System.out.println("You can't eject, you haven't inserted a quarter yet");
                } else {
                    controller.returnQuarters();
                    state = State.NO_QUARTER;
                }
                break;
            case SOLD:
                if (controller.getQuartersCount() == 0) {
                    System.out.println("You can't eject, you haven't inserted a quarter yet");
                } else {
                    controller.returnQuarters();
                    state = State.NO_QUARTER;
                }
                break;
            case NO_QUARTER:
                System.out.println("You haven't inserted a quarter");
                break;
            case HAS_QUARTER:
                controller.returnQuarters();
                state = State.NO_QUARTER;
                break;
        }
    }

    public void turnCrank() {
        switch (state) {
            case SOLD_OUT:
                System.out.println("You turned but there's no gumballs");
                break;
            case SOLD:
                System.out.println("Turning twice doesn't get you another gumball");
                break;
            case NO_QUARTER:
                System.out.println("You turned but there's no quarter");
                break;
            case HAS_QUARTER:
                System.out.println("You turned...");
                controller.useQuarter();
                state = State.SOLD;
                this.dispence();
                break;
        }
    }

    public void refill(int numBalls) throws WrongAmountException {
        if (numBalls < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");

        }
        switch (state) {
            case SOLD_OUT:
                count = numBalls;
                if (count != 0 && controller.getQuartersCount() == 0) {
                    state = State.NO_QUARTER;
                }
                break;
            case SOLD:
                System.out.println("Cant refill machine in SOLD state");
                break;
            case NO_QUARTER:
                count = numBalls;
                if (count == 0) {
                    state = State.SOLD_OUT;
                }
                break;
            case HAS_QUARTER:
                count = numBalls;
                if (count == 0) {
                    state = State.SOLD_OUT;
                }
                break;
        }
    }

    public String toString() {
        String state = State.toString(this.state);
        String result = "Mighty Gumball, Inc.\r\n" +
                "C++-enabled Standing Gumball Model #2016\r\n" +
                "Inventory: " + count + " gumball" + (count != 1 ? "s" : "") + "\r\n" +
                "Machine is " + state + "\r\n";
        return result;
    }

    public void dispence() {
        switch (state) {
            case SOLD_OUT:
                System.out.println("No gumball dispensed.");
                break;
            case SOLD:
                System.out.println("A gumball comes rolling out the slot");
                --count;
                if (count == 0) {
                    System.out.println("Oops, out of gumballs");
                    state = State.SOLD_OUT;
                } else if (controller.getQuartersCount() == 0) {
                    state = State.NO_QUARTER;
                } else {
                    state = State.HAS_QUARTER;
                }
                break;
            case NO_QUARTER:
                System.out.println("You need to pay first");
                break;
            case HAS_QUARTER:
                System.out.println("No gumball dispensed");
                break;
        }
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

    public QuartersController getQuartersController() {
        return controller;
    }

}
