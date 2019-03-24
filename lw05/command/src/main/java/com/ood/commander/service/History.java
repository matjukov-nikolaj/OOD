package com.ood.commander.service;

import com.ood.commander.command.Command;

import java.util.ArrayList;
import java.util.List;

public class History implements Executor {

    private int nextCommandIndex;

    private List<Command> commands;

    public History() {
        this.nextCommandIndex = 0;
        this.commands = new ArrayList<>();
    }

    public boolean canUndo() {
        return this.nextCommandIndex != 0;
    }

    public boolean canRedo() {
        return this.nextCommandIndex != this.commands.size();
    }

    public void undo() {
        if (this.canUndo()) {
            this.commands.get(this.nextCommandIndex - 1).unExecute();
            --this.nextCommandIndex;
        }
    }

    public void redo() {
        if (this.canRedo()) {
            this.commands.get(this.nextCommandIndex).execute();
            ++this.nextCommandIndex;
        }
    }

    @Override
    public void addAndExecuteCommand(Command command) throws Exception {
        if (this.commands.size() == 10) {
            this.commands.remove(0);
            --this.nextCommandIndex;
        }

        List<Command> tempCommands = new ArrayList<>(this.commands);
        this.commands = this.commands.subList(0, this.nextCommandIndex);
        for (int i = this.nextCommandIndex; i < tempCommands.size(); ++i) {
            tempCommands.get(i).destroy();
        }
        this.commands = this.commands.subList(0, this.nextCommandIndex);

        if (this.nextCommandIndex < this.commands.size()) {
            command.execute();
            ++this.nextCommandIndex;
            this.commands.set(this.commands.size() - 1, command);
        } else {
            this.commands.add(null);
            try {
                command.execute();
                this.commands.set(this.commands.size() - 1, command);
                ++this.nextCommandIndex;
            } catch (Exception e) {
                this.commands.remove(this.commands.size() - 1);
                throw new Exception();
            }
        }
    }

}
