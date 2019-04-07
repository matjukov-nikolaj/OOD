package com.ood.state.naive;

import com.ood.WrongAmountException;

public class GumballMachine {

    private int count;

    private State state = State.SOLD_OUT;

    public GumballMachine(int count) throws WrongAmountException {
        if (count < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");

        }
        this.count = count;
        this.state = count > 0 ? State.NO_QUARTER : State.SOLD_OUT;
    }

    public void insertQuarter() {
        switch (state) {
            case SOLD_OUT:
                System.out.println("You can't insert a quarter, the machine is sold out");
                break;
            case SOLD:
                System.out.println("Please wait, we're already giving you a gumball");
                break;
            case NO_QUARTER:
                System.out.println("You inserted a quarter");
                break;
            case HAS_QUARTER:
                System.out.println("You can't insert another quarter");
                break;
        }
    }

    public void ejectQuarter() {
        switch (state) {
            case SOLD_OUT:
                System.out.println("You can't eject, you haven't inserted a quarter yet");
                break;
            case SOLD:
                System.out.println("Sorry you already turned the crank");
                break;
            case NO_QUARTER:
                System.out.println("You haven't inserted a quarter");
                break;
            case HAS_QUARTER:
                System.out.println("Quarter returned");
                state = State.NO_QUARTER;
                break;
        }
    }

    public void TurnCrank() {
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
                state = State.SOLD;
                this.dispence();
                break;
        }
    }

    public void refill(int numBalls) throws WrongAmountException {
        if (numBalls < 0) {
            throw new WrongAmountException("Count of gumballs cant be less than zero.");

        }
        this.count = numBalls;
        this.state = numBalls > 0 ? State.NO_QUARTER : State.SOLD_OUT;
    }

    public String toString() {
        String state = State.toString(this.state);
        String result = "Mighty Gumball, Inc.\r\n" +
                "C++-enabled Standing Gumball Model #2016\r\n" +
                "Inventory: " + count + " gumball" + (count != 1 ? "s" : "") +"\r\n" +
                "Machine is " + state +"\r\n";
        return result;
    }

    private void dispence() {
        switch (state) {
            case SOLD_OUT:
            case SOLD:
                System.out.println("A gumball comes rolling out the slot");
                --count;
                if (count == 0) {
                    System.out.println("Oops, out of gumballs");
                    state = State.SOLD_OUT;
                } else {
                    state = State.NO_QUARTER;
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


}
