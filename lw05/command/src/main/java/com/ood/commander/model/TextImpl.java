package com.ood.commander.model;

public class TextImpl implements Text {

    private String text;

    public TextImpl(String text) {
        this.text = text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
