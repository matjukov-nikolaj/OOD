package com.ood.commander.command;

public interface Command extends Removable {

    void execute();

    void unExecute();

}
