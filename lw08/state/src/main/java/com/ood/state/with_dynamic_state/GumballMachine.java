package com.ood.state.with_dynamic_state;

public interface GumballMachine {

    void releaseBall();

    int getBallCount();

    void setSoldOutState();

    void setNoQuarterState();

    void setSoldState();

    void setHasQuarterState();

}
