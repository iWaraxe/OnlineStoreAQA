package com.denis.consoleapp.service;

public class PrintTopPriceCommand implements Command {
    private CommandService commandService;

    public PrintTopPriceCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void execute() {
        commandService.printTopPrice();
    }

}
