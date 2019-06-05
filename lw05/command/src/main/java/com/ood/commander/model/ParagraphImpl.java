package com.ood.commander.model;

import com.ood.commander.command.ChangeStringCommand;
import com.ood.commander.service.Executor;

public class ParagraphImpl implements Paragraph {

    private String text;

    private Executor executor;

    public ParagraphImpl(String text, Executor executor) {
        this.text = text;
        this.executor = executor;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getParagraphText() {
        return this.text;
    }

    @Override
    public void setParagraphText(String text) throws Exception {
        this.executor.addAndExecuteCommand(new ChangeStringCommand(this.text, text, this));
    }
}
