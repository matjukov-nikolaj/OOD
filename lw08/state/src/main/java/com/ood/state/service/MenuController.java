package com.ood.state.service;

import com.ood.state.with_state.GumballMachineImpl;

import java.util.List;

public class MenuController {

    private Menu menu;

    private GumballMachineImpl gumballMachine;

    private static final String EXIT = "Exit";

    private static final String INSERT_QUARTER = "InsertQuarter";

    private static final String EJECT_QUARTER = "EjectQuarter";

    private static final String REFILL = "Refill";

    private static final String TURN_CRANK = "TurnCrank";

    private static final String TO_STRING = "ToString";

    private static final String WITHOUT_PARAMETERS = "Without parameters.";

    private static final String HELP = "Help";

    public MenuController() {
        this.menu = new Menu();
        try {
            this.gumballMachine = new GumballMachineImpl(10);
            this.menu.addItem(HELP, HELP + ". " + WITHOUT_PARAMETERS, this.showHelpBlock());
            this.menu.addItem(EJECT_QUARTER, EJECT_QUARTER  + ". " + WITHOUT_PARAMETERS, this.ejectQuarterCommand());
            this.menu.addItem(TURN_CRANK, TURN_CRANK  + ". " + WITHOUT_PARAMETERS, this.turnCrankCommand());
            this.menu.addItem(TO_STRING, TO_STRING  + ". " + WITHOUT_PARAMETERS, this.toStringCommand());
            this.menu.addItem(REFILL, REFILL  + ". " + "<Number of gumballs>", this.refillCommand());
            this.menu.addItem(INSERT_QUARTER, INSERT_QUARTER  + ". " + WITHOUT_PARAMETERS, this.insertQuarterCommand());
            this.menu.addItem(EXIT, EXIT + ". " + WITHOUT_PARAMETERS, this.exit());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        this.menu.run();
    }

    private void showErrorForCommand(String message) {
        System.out.println("Incorrect number of parameter for \"" + message + "\" command.");
        this.menu.showInstruction();
    }

    private Function refillCommand() {
        return (List<String> args) -> {
            if (args.size() != 2) {
                this.showErrorForCommand(EXIT);
                return;
            }
            int number = Integer.parseInt(args.get(1));
            try {
                gumballMachine.refill(number);
            } catch (Exception e) {
                System.out.println(e.getClass() + " - " + e.getMessage());
            }
        };
    }

    private Function toStringCommand() {
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(EXIT);
                return;
            }
            System.out.println(this.gumballMachine.toString());
        };
    }

    private Function turnCrankCommand() {
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(EXIT);
                return;
            }
            this.gumballMachine.turnCrank();
        };
    }

    private Function ejectQuarterCommand() {
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(EXIT);
                return;
            }
            this.gumballMachine.ejectQuarter();
        };
    }

    private Function insertQuarterCommand() {
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(EXIT);
                return;
            }
            this.gumballMachine.insertQuarter();
        };
    }

    private Function exit() {
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(EXIT);
                return;
            }
            this.menu.exit();
        };
    }

    private Function showHelpBlock() {
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(HELP);
                return;
            }
            try {
                this.menu.showInstruction();
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }
}
