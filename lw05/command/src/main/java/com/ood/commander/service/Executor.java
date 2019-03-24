package com.ood.commander.service;

import com.ood.commander.command.Command;

public interface Executor {

    void addAndExecuteCommand(Command command) throws Exception;

}
