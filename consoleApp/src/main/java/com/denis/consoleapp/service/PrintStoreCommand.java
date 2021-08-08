package com.denis.consoleapp.service;

public class PrintStoreCommand implements Command {
    private CommandService commandService;

    public PrintStoreCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void execute() {
        commandService.printStore();
    }

}
