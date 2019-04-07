package com.ood.state.with_state;

import com.ood.exception.WrongAmountException;

public interface State {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispence();

    String toString();

    void refill(int ballsCount) throws WrongAmountException;

}
