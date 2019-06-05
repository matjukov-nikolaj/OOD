package com.ood.state.service;

import java.util.List;

@FunctionalInterface
public interface Function {

    void action(List<String> args);

}
