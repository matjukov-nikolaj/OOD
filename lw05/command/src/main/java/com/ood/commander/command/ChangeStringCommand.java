package com.ood.commander.command;

import com.ood.Utilities;

public class ChangeStringCommand extends AbstractCommand {

    private String newValue;

    private String target;

    public ChangeStringCommand(String target, String newValue) {
        this.target = target;
        this.newValue = newValue;
    }

    @Override
    protected void doExecute() {
        this.newValue = this.newValue + this.target;
        this.target = this.newValue.substring(0, (this.newValue.length() - this.target.length()));
        this.newValue = this.newValue.substring(this.target.length());
    }

    @Override
    protected void doUnExecute() {
        this.newValue = this.newValue + this.target;
        this.target = this.newValue.substring(0, (this.newValue.length() - this.target.length()));
        this.newValue = this.newValue.substring(this.target.length());
    }
}
