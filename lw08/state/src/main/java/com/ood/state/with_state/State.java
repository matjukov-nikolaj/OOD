package com.ood.state.with_state;

public interface State {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispence();

    String toString();

}
