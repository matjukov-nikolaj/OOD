package com.ood.state.with_state;

public interface GumballMachine {

    void releaseBall();

    int getBallCount();

    void setSoldOutState();

    void setNoQuarterState();

    void setSoldState();

    void setHasQuarterState();

}
