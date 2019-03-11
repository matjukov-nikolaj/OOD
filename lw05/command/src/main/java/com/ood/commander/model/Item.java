package com.ood.commander.model;

public class Item {

    private String shortcut;

    private String description;

    private Function func;

    public Item(String shortcut, String description, Function func) {
        this.shortcut = shortcut;
        this.description = description;
        this.func = func;
    }

    public String getDescription() {
        return description;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void action(String[] args) {
        this.func.action(args);
    }

}
