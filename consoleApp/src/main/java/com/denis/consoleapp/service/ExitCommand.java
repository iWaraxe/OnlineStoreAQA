package com.denis.consoleapp.service;

import com.denis.store.Store;

public class ExitCommand implements Command {
    private Store store;

    public ExitCommand(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}