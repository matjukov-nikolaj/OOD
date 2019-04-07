package com.ood.state.with_dynamic_state;

public interface State {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispence();

    String toString();

}
