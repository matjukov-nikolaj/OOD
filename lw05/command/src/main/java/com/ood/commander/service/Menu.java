package com.ood.commander.service;

import com.ood.commander.model.Function;
import com.ood.commander.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private List<Item> items;

    private boolean exit;

    public Menu() {
        this.items = new ArrayList<>();
        this.exit = false;
    }

    public void addItem(String shortcut, String description, Function function) {
        this.items.add(new Item(shortcut, description, function));
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        while (true) {
            if (this.exit) {
                return;
            }
            System.out.print("> ");
            String inputStr = input.next().trim();
            if (!this.executeCommand(inputStr)) {
                return;
            }
        }
    }

    public void showInstruction() {
        System.out.println("Command list:\n");
        for (Item item : this.items) {
            System.out.println(item.getShortcut() + ": " + item.getDescription());
        }
    }

    public void exit() {
        this.exit = true;
    }

    private boolean executeCommand(String command) {
        String[] commandList = command.toLowerCase().split(" ");
        String name = commandList[0];
        this.exit = false;
        Item item = this.findCommandByName(name);

        if (item != null) {
            item.action(Arrays.asList(commandList));
        } else {
            System.out.println("Unknown command.");
        }
        return !this.exit;
    }

    private Item findCommandByName(String name) {
        for (Item item: this.items) {
            if (item.getShortcut().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }


}
