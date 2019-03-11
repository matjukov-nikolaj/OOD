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
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) throws Exception {
        this.history.addAndExecuteCommand(new ChangeStringCommand(this.text, text));
        this.text = text;
    }
}
