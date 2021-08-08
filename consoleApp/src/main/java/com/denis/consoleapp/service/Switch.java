package com.denis.consoleapp.service;

public class Switch {
    private Command printTopPriceCommand;
    private Command exitCommand;
    private Command printSortCommand;
    private Command printStoreCommand;

    public Switch(Command printTopPriceCommand, Command exitCommand, Command printSortCommand, Command printStoreCommand) {
        this.printTopPriceCommand = printTopPriceCommand;
        this.exitCommand = exitCommand;
        this.printSortCommand = printSortCommand;
        this.printStoreCommand = printStoreCommand;
    }

    public void printTopPrice() {
        printTopPriceCommand.execute();
    }

    public void exit() {
        exitCommand.execute();
    }

    public void printSort() {
        printSortCommand.execute();
    }

    public void printStore() {
        printStoreCommand.execute();
    }
}
