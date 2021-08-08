package com.denis.consoleapp.service;

public class PrintSortCommand implements Command {
    private CommandService commandService;

    public PrintSortCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void execute() {
        commandService.printSort();
    }
}

