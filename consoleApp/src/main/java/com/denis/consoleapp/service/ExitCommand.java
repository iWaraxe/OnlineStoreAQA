package com.denis.consoleapp.service;

public class ExitCommand implements Command {
    private CommandService commandService;

    public ExitCommand(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
