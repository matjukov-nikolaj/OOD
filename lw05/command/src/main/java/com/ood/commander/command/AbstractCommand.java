package com.ood.commander.command;

public abstract class AbstractCommand implements Command {

    public void execute() {
        if (!this.executed) {
            this.doExecute();
            executed = true;
        }
    }

    public void unExecute() {
        if (this.executed) {
            this.doUnExecute();
            executed = false;
        }
    }

    protected abstract void doExecute();

    protected abstract void doUnExecute();

    private boolean executed = false;

}
