package com.denis.consoleapp.service;

import com.denis.store.Store;

public class PrintStoreCommand extends Handler {
    public PrintStoreCommand(Store store) {
        super(store);
    }

    @Override
    public boolean handler(String command) {
        return "print".equalsIgnoreCase(command);
    }

    @Override
    public void execute(String command) {
        System.out.println(store);
    }
}