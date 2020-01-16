package com.ood.state.with_dynamic_state;

import com.ood.exception.WrongAmountException;
import com.ood.state.quarters.QuartersController;

public interface GumballMachine {

    void releaseBall();

    int getBallCount();

    void setBallsCount(int ballsCount) throws WrongAmountException;

    void setSoldOutState();

    void setNoQuarterState();

    void setSoldState();

    void setHasQuarterState();

    QuartersController getQuartersController();

}
