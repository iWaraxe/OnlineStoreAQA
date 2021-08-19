package com.denis.consoleapp.service;

import com.denis.store.Store;

public class ExitCommand extends Handler {
    public ExitCommand(Store store) {
        super(store);
    }

    @Override
    public boolean handler(String command) {
        return "quit".equalsIgnoreCase(command);
    }

    @Override
    public void execute(String command) {
        System.exit(0);
    }
}