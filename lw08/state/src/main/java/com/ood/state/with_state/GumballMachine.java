package com.ood.state.with_state;

import com.ood.exception.WrongAmountException;
import com.ood.state.service.QuartersController;

public interface GumballMachine {

    void releaseBall();

    int getBallCount();

    void setSoldOutState();

    void setNoQuarterState();

    void setSoldState();

    void setHasQuarterState();

    void refill(int numBalls) throws WrongAmountException;

    QuartersController getQuartersController();

}
