package com.ood.commander.model;

import com.ood.commander.service.History;
import com.ood.commander.command.ChangeStringCommand;

public class ParagraphImpl implements Paragraph {

    private String text;

    private History history;

    public ParagraphImpl(String text, History history) {
        this.text = text;
        this.history = history;
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
        this.history.addAndExecuteCommand(new ChangeStringCommand(this.text, text, this));
    }
}
