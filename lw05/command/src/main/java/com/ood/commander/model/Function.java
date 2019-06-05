package com.ood.commander.model;

import java.util.List;

@FunctionalInterface
public interface Function {

    void action(List<String> args);

}
