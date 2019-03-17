package com.ood.commander.command;

import com.ood.commander.model.Text;

public class ChangeStringCommand extends AbstractCommand {

    private String newValue;

    private String target;

    private Text text;

    public ChangeStringCommand(String target, String newValue, Text text) {
        this.target = target;
        this.newValue = newValue;
        this.text = text;
    }

    @Override
    public void remove() {

    }

    @Override
    protected void doExecute() {
        this.text.setText(this.newValue);
    }

    @Override
    protected void doUnExecute() {
        this.text.setText(this.target);
    }

}
