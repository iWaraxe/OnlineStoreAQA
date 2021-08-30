package com.denis.consoleapp.service;

import com.denis.store.Store;

public class ExitHandler extends Handler {

    @Override
    public boolean isAccessibleHandler(String handler) {
        return "quit".equalsIgnoreCase(handler);
    }

    @Override
    public void execute(Store store) {
        System.exit(0);
    }
}